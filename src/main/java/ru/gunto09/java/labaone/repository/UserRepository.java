package ru.gunto09.java.labaone.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gunto09.java.labaone.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
