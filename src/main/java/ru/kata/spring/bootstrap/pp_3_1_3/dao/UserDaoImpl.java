package ru.kata.spring.bootstrap.pp_3_1_3.dao;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.kata.spring.bootstrap.pp_3_1_3.entity.Role;
import ru.kata.spring.bootstrap.pp_3_1_3.entity.User;
import ru.kata.spring.bootstrap.pp_3_1_3.repository.RoleRepository;
import ru.kata.spring.bootstrap.pp_3_1_3.repository.UserRepository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Repository
public class UserDaoImpl implements UserDao, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public UserDaoImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        roleRepository.save(new Role(1L,"ROLE_ADMIN"));
        roleRepository.save(new Role(2L,"ROLE_USER"));
        userRepository.save(new User("admin","admin",35,"admin@mail.ru","{noop}admin", roleRepository.findById(1L).stream().collect(Collectors.toList())));
        userRepository.save(new User("user","user",30,"user@mail.ru","{noop}user", roleRepository.findById(2L).stream().collect(Collectors.toList())));


    }


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
        if (user.getPassword() != null) {
            if (!user.getPassword().startsWith("{noop}")) {
                user.setPassword("{noop}" + user.getPassword());
                userRepository.save(user);
            }
        }
    }

    @Override
    public User getById(long id) {
        return userRepository.getById(id);
    }
    @Override
    public void removeById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByUsername(username);
        return user;
    }
}
