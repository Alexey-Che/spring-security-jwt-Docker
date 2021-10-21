package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.entity.RoleEntity;
import com.example.springsecurityjwt.entity.UserEntity;
import com.example.springsecurityjwt.repository.RoleEntityRepository;
import com.example.springsecurityjwt.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserEntityRepository userEntityRepository;
    private final RoleEntityRepository roleEntityRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserEntityRepository userEntityRepository, RoleEntityRepository roleEntityRepository, PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.roleEntityRepository = roleEntityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserEntity userEntity) {
        if(roleEntityRepository.findByName("ROLE_USER") == null) {
            roleEntityRepository.save(new RoleEntity("ROLE_USER"));
        }
        RoleEntity userRole = roleEntityRepository.findByName("ROLE_USER");
        userEntity.setRoleEntity(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntityRepository.save(userEntity);
    }

    public UserEntity findByLogin(String login) {
        return userEntityRepository.findByLogin(login);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }


}
