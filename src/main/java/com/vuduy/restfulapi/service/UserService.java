package com.vuduy.restfulapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vuduy.restfulapi.domain.User;
import com.vuduy.restfulapi.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository; // Thêm final để pro hơn

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create User
    public User handleUser(User newUser) {
        return this.userRepository.save(newUser);
    }

    // fetch all user
    public List<User> handleGetAllUser() {
        return this.userRepository.findAll();
    }

    // fetch user by id
    public User handleGetUserById(Long id) {
        Optional<User> checkIdUser = this.userRepository.findById(id);
        if (checkIdUser.isPresent()) {
            return checkIdUser.get();
        } else {
            return null;
        }
    }

    // fetch user by id and update user
    public User handleUpdateUser(Long id, User dataUser) {
        Optional<User> checkIdUser = this.userRepository.findById(id);
        if (checkIdUser.isPresent()) {

            User currentUser = checkIdUser.get();
            currentUser.setName(dataUser.getName());
            currentUser.setEmail(dataUser.getEmail());
            currentUser.setPassword(dataUser.getPassword());
            return this.userRepository.save(currentUser);
        } else {
            return null;
        }
    }

    // fetch user by id and delete user
    public void handleDeleteUser(Long id) {
        this.userRepository.deleteById(id);
    }
}
