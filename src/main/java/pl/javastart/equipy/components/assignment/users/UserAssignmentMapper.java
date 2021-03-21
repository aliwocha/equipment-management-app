package pl.javastart.equipy.components.assignment.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.equipy.components.asset.Asset;
import pl.javastart.equipy.components.asset.AssetRepository;
import pl.javastart.equipy.components.assignment.common.Assignment;
import pl.javastart.equipy.components.assignment.common.AssignmentDto;
import pl.javastart.equipy.components.assignment.common.AssignmentMapper;

import java.util.Optional;

@Service
public class UserAssignmentMapper implements AssignmentMapper<UserAssignmentDto> {

    private AssetRepository assetRepository;

    @Autowired
    public UserAssignmentMapper(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public AssignmentDto toDto(Assignment assignment) {
        UserAssignmentDto dto = new UserAssignmentDto();
        dto.setId(assignment.getId());
        dto.setStart(assignment.getStart());
        dto.setEnd(assignment.getEnd());
        if(assignment.getAsset() != null) {
            dto.setAssetId(assignment.getAsset().getId());
            dto.setAssetName(assignment.getAsset().getName());
            dto.setAssetSerialNumber(assignment.getAsset().getSerialNumber());
        }
        return dto;
    }

    @Override
    public Assignment toEntity(UserAssignmentDto assignment) {
        Assignment entity = new Assignment();
        entity.setId(assignment.getId());
        entity.setStart(assignment.getStart());
        entity.setEnd(assignment.getEnd());
        Optional<Asset> asset = assetRepository.findById(assignment.getAssetId());
        asset.ifPresent(entity::setAsset);
        return entity;
    }
}
