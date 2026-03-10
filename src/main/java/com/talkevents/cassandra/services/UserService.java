package com.talkevents.cassandra.services;

import com.talkevents.cassandra.models.User;
import com.talkevents.cassandra.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID());
        }

        return userRepository.save(user);
    }

    public void update(User user) {
        var userToUpdate = getById(user.getId());

        if (userToUpdate == null) {
            return;
        }

        userToUpdate.setName(user.getName());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setEmail(user.getEmail());

        userRepository.save(userToUpdate);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}