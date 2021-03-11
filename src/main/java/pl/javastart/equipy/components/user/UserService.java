package pl.javastart.equipy.components.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
class UserService {

    private UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Użytkownik o wskazanym id nie istnieje"));
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

    UserDto save(UserDto user) {
        if(user.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zapisywany obiekt nie może mieć ustawionego id");

        Optional<User> userByPesel = userRepository.findByPesel(user.getPesel());
        if(userByPesel.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Użytkownik z tym numerem pesel już istnieje");
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
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Użytkownik z tym numerem pesel już istnieje");
        });

        return mapAndSaveUser(user);
    }

    private UserDto mapAndSaveUser(UserDto user) {
        User userEntity = UserMapper.toEntity(user);
        User savedUser = userRepository.save(userEntity);
        return UserMapper.toDto(savedUser);
    }
}
