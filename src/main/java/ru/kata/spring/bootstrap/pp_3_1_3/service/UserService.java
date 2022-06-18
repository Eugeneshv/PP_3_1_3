package ru.kata.spring.bootstrap.pp_3_1_3.service;


import ru.kata.spring.bootstrap.pp_3_1_3.entity.Role;
import ru.kata.spring.bootstrap.pp_3_1_3.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    void save(User user);
    User getById(long id);
    void removeById(long id);

    List<Role> listRoles();
    User loadUserByUsername(String username);
}
