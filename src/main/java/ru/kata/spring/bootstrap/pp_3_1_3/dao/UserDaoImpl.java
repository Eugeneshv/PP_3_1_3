package ru.kata.spring.bootstrap.pp_3_1_3.dao;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.kata.spring.bootstrap.pp_3_1_3.entity.Role;
import ru.kata.spring.bootstrap.pp_3_1_3.entity.User;
import ru.kata.spring.bootstrap.pp_3_1_3.repository.RoleRepository;
import ru.kata.spring.bootstrap.pp_3_1_3.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
        List<Role> roleOne = new ArrayList<>();
        roleOne.add(roleRepository.getReferenceById(1L));
        List<Role> roleTwo = new ArrayList<>();
        roleTwo.add(roleRepository.getReferenceById(2L));
        userRepository.save(new User("admin","admin",35,"admin@mail.ru","{noop}admin", roleOne));
        userRepository.save(new User("user","user",30,"user@mail.ru","{noop}user", roleTwo));

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
        return userRepository.getReferenceById(id);
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
        return userRepository.findByUsername(username);
    }
}
