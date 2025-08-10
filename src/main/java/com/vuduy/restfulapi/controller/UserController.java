package com.vuduy.restfulapi.controller;

import java.security.InvalidKeyException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vuduy.restfulapi.domain.User;
import com.vuduy.restfulapi.service.UserService;
import com.vuduy.restfulapi.service.error.IdInValidException;

@RestController
public class UserController {

    private final UserService userService; // Thêm final để pro hơn

    private final PasswordEncoder passwordEncoder; // Thêm final để pro hơn

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("users")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        String hashPassword = this.passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(hashPassword);
        User currentUser = this.userService.handleUser(newUser);
        return ResponseEntity.ok().body(currentUser);
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok().body(this.userService.handleGetAllUser());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.userService.handleGetUserById(id));
    }

    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User dataUser) {
        User currentUser = this.userService.handleUpdateUser(id, dataUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(currentUser);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws IdInValidException {
        if (id >= 1500) {
            throw new IdInValidException("ID không được lớn hơn 1500");
        }
        this.userService.handleDeleteUser(id);
        return ResponseEntity.ok().body("Delete User");
    }
}
