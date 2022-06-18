package ru.kata.spring.bootstrap.pp_3_1_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.bootstrap.pp_3_1_3.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
