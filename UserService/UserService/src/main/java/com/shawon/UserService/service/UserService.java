package com.shawon.UserService.service;

import com.shawon.UserService.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createOrUpdateUser(User user);

    Optional<User> getUserById(String id);

    void deleteUserById(String id);

    List<User> getAllUsers();

    List<User> getUserNamesAndEmails();
}
