package com.example.moodle.service;

import com.example.moodle.model.User;
import com.example.moodle.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public User addUser(User user);
    public User getUserById(Long id);
    public List<User> getAllUsers();
    public void deleteUserById(Long id);
}
