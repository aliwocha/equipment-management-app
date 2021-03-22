package pl.javastart.equipy.components.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.javastart.equipy.components.asset.AssetRepository;
import pl.javastart.equipy.components.user.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
class AssignmentService {

    private AssignmentRepository assignmentRepository;
    private UserRepository userRepository;
    private AssetRepository assetRepository;
    private AssignmentMapper assignmentMapper;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository, UserRepository userRepository,
                             AssetRepository assetRepository, AssignmentMapper assignmentMapper) {
        this.assignmentRepository = assignmentRepository;
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
        this.assignmentMapper = assignmentMapper;
    }

    AssignmentDto createAssignment(AssignmentDto assignment) {
        if(assignment.getUserId() == null || assignment.getAssetId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId i assetId nie mogą mieć wartości null");
        }

        userRepository.findById(assignment.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Użytkownik o podanym id nie istnieje"));

        assetRepository.findById(assignment.getAssetId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wyposażenie o podanym id nie istnieje"));

        Optional<Assignment> assignmentByAssetId = assignmentRepository.findByAsset_IdAndEndIsNull(assignment.getAssetId());
        if(assignmentByAssetId.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wyposażenie o podanym id jest aktualnie wypożyczone");
        }

        Assignment assignmentEntity = assignmentMapper.toEntity(assignment);
        Assignment savedAssignment = assignmentRepository.save(assignmentEntity);
        return assignmentMapper.toDto(savedAssignment);
    }

    LocalDateTime finishAssignment(Long id) {
        Optional<Assignment> assignment = assignmentRepository.findById(id);
        Assignment updatedAssignment = assignment.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Brak przypisania o podanym id"));
        if(updatedAssignment.getEnd() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wyposażenie o podanym id zostało już zwrócone");
        } else {
            updatedAssignment.setEnd(LocalDateTime.now().withNano(0));
            assignmentMapper.toDto(assignmentRepository.save(updatedAssignment));
        }
        return updatedAssignment.getEnd();
    }
}
