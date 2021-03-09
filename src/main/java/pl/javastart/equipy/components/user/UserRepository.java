package pl.javastart.equipy.components.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByLastNameContainingIgnoreCase(String lastName);
}
