package ru.gunto09.java.labaone.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gunto09.java.labaone.model.UserRole;

public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
}
