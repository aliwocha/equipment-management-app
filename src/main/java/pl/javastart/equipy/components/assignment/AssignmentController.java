package pl.javastart.equipy.components.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private AssignmentService assignmentService;

    @Autowired
    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public AssignmentDto saveAssignment(@RequestBody AssignmentDto assignment) {
        return assignmentService.createAssignment(assignment);
    }

    @PostMapping("/{id}/end")
    public LocalDateTime finishAssignment(@PathVariable Long id) {
        return assignmentService.finishAssignment(id);
    }
}
