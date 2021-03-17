package pl.javastart.equipy.components.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.equipy.components.asset.Asset;
import pl.javastart.equipy.components.asset.AssetRepository;

import java.util.Optional;

@Service
class AssignmentMapper {

    private AssetRepository assetRepository;

    @Autowired
    AssignmentMapper(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    AssignmentDto toDto(Assignment assignment) {
        AssignmentDto dto = new AssignmentDto();
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

    Assignment toEntity(AssignmentDto assignment) {
        Assignment entity = new Assignment();
        entity.setId(assignment.getId());
        entity.setStart(assignment.getStart());
        entity.setEnd(assignment.getEnd());
        Optional<Asset> asset = assetRepository.findById(assignment.getAssetId());
        asset.ifPresent(entity::setAsset);
        return entity;
    }
}
