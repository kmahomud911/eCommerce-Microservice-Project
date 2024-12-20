package com.shawon.UserService.service;

import com.shawon.UserService.model.User;
import com.shawon.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Cacheable(value = "users", key = "#id")
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    @CacheEvict(value = "users", key = "#id")
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUserNamesAndEmails() {
        return userRepository.findAllEmailAndNames();
    }
}
