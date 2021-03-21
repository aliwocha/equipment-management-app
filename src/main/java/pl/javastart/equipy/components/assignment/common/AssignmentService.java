package pl.javastart.equipy.components.assignment.common;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.javastart.equipy.components.asset.Asset;
import pl.javastart.equipy.components.asset.AssetRepository;
import pl.javastart.equipy.components.assignment.assets.AssetAssignmentMapper;
import pl.javastart.equipy.components.assignment.users.UserAssignmentMapper;
import pl.javastart.equipy.components.user.User;
import pl.javastart.equipy.components.user.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class AssignmentService {

    private AssignmentRepository assignmentRepository;
    private UserRepository userRepository;
    private AssetRepository assetRepository;
    private UserAssignmentMapper userAssignmentMapper;
    private AssetAssignmentMapper assetAssignmentMapper;

    public AssignmentService(AssignmentRepository assignmentRepository, UserRepository userRepository,
                             AssetRepository assetRepository, UserAssignmentMapper userAssignmentMapper,
                             AssetAssignmentMapper assetAssignmentMapper) {
        this.assignmentRepository = assignmentRepository;
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
        this.userAssignmentMapper = userAssignmentMapper;
        this.assetAssignmentMapper = assetAssignmentMapper;
    }

    List<AssignmentDto> findAllByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Użytkownik o podanym id nie istnieje");
        }

        return assignmentRepository.findAllByUserId(userId)
                .stream()
                .map(userAssignmentMapper::toDto)
                .collect(Collectors.toList());
    }

    List<AssignmentDto> findAllByAssetId(Long assetId) {
        Optional<Asset> asset = assetRepository.findById(assetId);
        if(!asset.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sprzęt o podanym id nie istnieje");
        }

        return assignmentRepository.findAllByAssetId(assetId)
                .stream()
                .map(assetAssignmentMapper::toDto)
                .collect(Collectors.toList());
    }
}
