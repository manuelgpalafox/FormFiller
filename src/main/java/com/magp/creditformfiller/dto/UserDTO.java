package com.magp.creditformfiller.dto;


import com.magp.creditformfiller.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long id;
    @NotBlank(message = "Primer nombre no debe estar vacío")
    private String firstName;
    @NotBlank(message = "Primer apellido no debe estar vacío")
    private String lastName;
    @NotBlank(message = "E-mail no debe estar vacío")
    @Email
    private String email;
    @NotBlank(message = "Contraseña no debe estar vacío")
    private String password;

    @NotBlank(message = "Debe seleccionar al menos 1 rol")
    private String role;

    private List<String> roles = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(Long id, String firstName, String lastName, String email, String password, List<String> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.roles = roles;
    }

    //    public UserDTO(Long id, String firstName, String lastName, String email, String password, String role) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.role = role;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
