package pl.javastart.equipy.components.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.equipy.components.assignment.Assignment;
import pl.javastart.equipy.components.user.User;
import pl.javastart.equipy.components.user.UserRepository;

import java.util.Optional;

@Service
class AssetAssignmentMapper {

    private UserRepository userRepository;

    @Autowired
    AssetAssignmentMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    AssetAssignmentDto toDto(Assignment assignment) {
        AssetAssignmentDto dto = new AssetAssignmentDto();
        dto.setId(assignment.getId());
        dto.setStart(assignment.getStart());
        dto.setEnd(assignment.getEnd());
        if(assignment.getUser() != null) {
            dto.setUserId(assignment.getUser().getId());
            dto.setFirstName(assignment.getUser().getFirstName());
            dto.setLastName(assignment.getUser().getLastName());
            dto.setPesel(assignment.getUser().getPesel());
        }
        return dto;
    }

    Assignment toEntity(AssetAssignmentDto assignment) {
        Assignment entity = new Assignment();
        entity.setId(assignment.getId());
        entity.setStart(assignment.getStart());
        entity.setEnd(assignment.getEnd());
        Optional<User> user = userRepository.findById(assignment.getUserId());
        user.ifPresent(entity::setUser);
        return entity;
    }
}
