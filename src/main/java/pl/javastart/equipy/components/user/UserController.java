package pl.javastart.equipy.components.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Optional<UserDto> getOne(@PathVariable Long id) {
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
}
