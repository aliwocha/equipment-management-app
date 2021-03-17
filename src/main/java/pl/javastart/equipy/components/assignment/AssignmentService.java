package pl.javastart.equipy.components.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.javastart.equipy.components.user.User;
import pl.javastart.equipy.components.user.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class AssignmentService {

    private AssignmentRepository assignmentRepository;
    private AssignmentMapper assignmentMapper;
    private UserRepository userRepository;

    @Autowired
    AssignmentService(AssignmentRepository assignmentRepository,
                             AssignmentMapper assignmentMapper, UserRepository userRepository) {
        this.assignmentRepository = assignmentRepository;
        this.assignmentMapper = assignmentMapper;
        this.userRepository = userRepository;
    }

    List<AssignmentDto> findAllByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Użytkownik o podanym id nie istnieje");
        }

        return assignmentRepository.findAllByUserId(userId)
                .stream()
                .map(assignmentMapper::toDto)
                .collect(Collectors.toList());
    }
}
