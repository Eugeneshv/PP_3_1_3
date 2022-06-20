package ru.kata.spring.bootstrap.pp_3_1_3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.bootstrap.pp_3_1_3.dao.UserDao;
import ru.kata.spring.bootstrap.pp_3_1_3.entity.User;
import ru.kata.spring.bootstrap.pp_3_1_3.entity.Role;
import ru.kata.spring.bootstrap.pp_3_1_3.repository.RoleRepository;


import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleRepository roleRepository) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void save(User user) { userDao.save(user);
    }

    @Override
    public void removeById(long id) {
        userDao.removeById(id);
    }

    @Override
    public List<Role> listRoles() { return userDao.listRoles(); }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.loadUserByUsername(username);
    }
}