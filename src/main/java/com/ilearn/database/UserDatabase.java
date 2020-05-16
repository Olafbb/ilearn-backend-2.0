package com.ilearn.database;

import com.ilearn.domain.User;
import com.ilearn.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDatabase {
    private UserRepository userRepository;

    public UserDatabase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public List<User> getUsersByEmail(String email) {
        return userRepository.findAllByEmail(email);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserByStudentId(Long id) {
        return userRepository.findByStudentId(id);
    }
}
