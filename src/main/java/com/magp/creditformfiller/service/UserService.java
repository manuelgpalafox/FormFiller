package com.magp.creditformfiller.service;

import com.magp.creditformfiller.dto.UserDTO;
import com.magp.creditformfiller.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDTO userDTO);

    void saveUser(User user);

    User findUserByEmail(String email);

    List<UserDTO> findAllUsers();

    User findById(Long id);

    void deleteById(Long id);

}
