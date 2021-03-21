package pl.javastart.equipy.components.assignment.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AssignmentController {

    private AssignmentService assignmentService;

    @Autowired
    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("/users/{userId}/assignments")
    public List<AssignmentDto> getAllUserAssignments(@PathVariable Long userId) {
        return assignmentService.findAllByUserId(userId);
    }

    @GetMapping("/assets/{assetId}/assignments")
    public List<AssignmentDto> getAllAssetAssignments(@PathVariable Long assetId) {
        return assignmentService.findAllByAssetId(assetId);
    }
}
