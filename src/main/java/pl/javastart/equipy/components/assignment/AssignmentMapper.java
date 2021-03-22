package pl.javastart.equipy.components.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.equipy.components.asset.Asset;
import pl.javastart.equipy.components.asset.AssetRepository;
import pl.javastart.equipy.components.user.User;
import pl.javastart.equipy.components.user.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
class AssignmentMapper {

    private AssetRepository assetRepository;
    private UserRepository userRepository;

    @Autowired
    public AssignmentMapper(AssetRepository assetRepository, UserRepository userRepository) {
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    AssignmentDto toDto(Assignment assignment) {
        AssignmentDto dto = new AssignmentDto();
        dto.setId(assignment.getId());
        dto.setStart(assignment.getStart());
        dto.setEnd(assignment.getEnd());
        if(assignment.getAsset() != null) {
            dto.setAssetId(assignment.getAsset().getId());
        }
        if(assignment.getUser() != null) {
            dto.setUserId(assignment.getUser().getId());
        }
        return dto;
    }

    Assignment toEntity(AssignmentDto assignment) {
        Assignment entity = new Assignment();
        Optional<Asset> asset = assetRepository.findById(assignment.getAssetId());
        asset.ifPresent(entity::setAsset);
        Optional<User> user = userRepository.findById(assignment.getUserId());
        user.ifPresent(entity::setUser);
        entity.setStart(LocalDateTime.now().withNano(0));
        return entity;
    }
}
