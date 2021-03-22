package pl.javastart.equipy.components.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getOne(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("")
    public List<UserDto> getAll(@RequestParam(required = false) String lastName) {
        if(lastName != null) {
            return userService.findAllByLastName(lastName);
        } else {
            return userService.findAll();
        }
    }

    @GetMapping("/{userId}/assignments")
    public List<UserAssignmentDto> getUserAssignments(@PathVariable Long userId) {
        return userService.getUserAssignments(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public UserDto save(@RequestBody UserDto user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public UserDto update(@RequestBody UserDto user, @PathVariable Long id) {
        return userService.update(user, id);
    }
}
