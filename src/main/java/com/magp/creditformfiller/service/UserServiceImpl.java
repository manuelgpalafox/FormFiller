package com.magp.creditformfiller.service;

import com.magp.creditformfiller.dao.RoleRepository;
import com.magp.creditformfiller.dao.UserRepository;
import com.magp.creditformfiller.dto.UserDTO;
import com.magp.creditformfiller.entity.Role;
import com.magp.creditformfiller.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getFirstName().concat(" ").concat(userDTO.getLastName()).trim());
        user.setEmail(userDTO.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Role role = roleRepository.findByName(userDTO.getRole());
        if(role == null){
            role = checkRoleExist(userDTO.getRole());
        }

        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public void saveUser(User user) {

        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public User findById(Long id) {
        Optional<User> result = userRepository.findById(id);

        User user = null;

        if(result.isPresent()){
            user = result.get();
        }else{
            // we didn't find the employee
            throw new RuntimeException("Did not find User id - " + id);
        }

        return user;
    }

    @Override
    public void deleteById(Long id) {

        // Eliminar las relaciones en la tabla users_roles
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.getRoles().clear(); // Eliminar todas las relaciones
            userRepository.deleteById(id);
        }

    }

    private UserDTO mapToUserDto(User user){
        UserDTO userDto = new UserDTO();
        String[] str = user.getName().split(" ");
        userDto.setId(user.getId());
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());

        userDto.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));

        return userDto;
    }

    private Role checkRoleExist(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }

}
