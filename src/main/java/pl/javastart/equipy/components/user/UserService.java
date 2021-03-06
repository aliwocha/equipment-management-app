package pl.javastart.equipy.components.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.javastart.equipy.components.user.exceptions.DuplicatePeselException;
import pl.javastart.equipy.components.user.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
class UserService {

    private UserRepository userRepository;
    private UserAssignmentMapper userAssignmentMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserAssignmentMapper userAssignmentMapper) {
        this.userRepository = userRepository;
        this.userAssignmentMapper = userAssignmentMapper;
    }

    UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(UserNotFoundException::new);
    }

    List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    List<UserDto> findAllByLastName(String lastName) {
        return userRepository.findAllByLastNameContainingIgnoreCase(lastName)
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    List<UserAssignmentDto> getUserAssignments(Long userId) {
        return userRepository.findById(userId)
                .map(User::getAssignments)
                .orElseThrow(UserNotFoundException::new)
                .stream()
                .map(userAssignmentMapper::toDto)
                .collect(Collectors.toList());
    }

    UserDto save(UserDto user) {
        if(user.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zapisywany obiekt nie może mieć ustawionego id");
        }

        Optional<User> userByPesel = userRepository.findByPesel(user.getPesel());
        if(userByPesel.isPresent()) {
            throw new DuplicatePeselException();
        }

        return mapAndSaveUser(user);
    }

    UserDto update(UserDto user, Long id) {
        if(!id.equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id aktualizowanego obiektu jest różne od id w ścieżce URL");
        }

        Optional<User> userByPesel = userRepository.findByPesel(user.getPesel());
        userByPesel.ifPresent(u -> {
            if(!u.getId().equals(user.getId()))
                throw new DuplicatePeselException();
        });

        return mapAndSaveUser(user);
    }

    private UserDto mapAndSaveUser(UserDto user) {
        User userEntity = UserMapper.toEntity(user);
        User savedUser = userRepository.save(userEntity);
        return UserMapper.toDto(savedUser);
    }
}
