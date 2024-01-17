package com.magp.creditformfiller.controller;

import com.magp.creditformfiller.dto.UserDTO;
import com.magp.creditformfiller.entity.Role;
import com.magp.creditformfiller.entity.User;
import com.magp.creditformfiller.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }


    // handler method to handle list of users
    @GetMapping("/list")
    public String showUsers(Model model) {
        List<UserDTO> users = userService.findAllUsers();

        // add to the spring model
        model.addAttribute("users", users);

        return "user/listUsers";
    }

    // handler method to handle home page request
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("modoEdicion", false);
        return "user/registration";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/registration/save")
    public String registration(@Valid UserDTO userDTO,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            return "user/registration";
        }

        User existingUser = userService.findUserByEmail(userDTO.getEmail());

        if (existingUser != null && existingUser.getEmail() != null) {

            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            return "user/registration";
        }

        userService.saveUser(userDTO);
        return "redirect:/users/registration?success";
    }

    @GetMapping("/registration/delete")
    public String updateUser(@RequestParam("userId") long userId,
                             Model model) {

        userService.deleteById(userId);

        return "redirect:/users/list";
    }

}
