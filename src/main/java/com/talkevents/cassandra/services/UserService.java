package com.talkevents.cassandra.services;

import com.talkevents.cassandra.models.User;
import com.talkevents.cassandra.repositories.AddressRepository;
import com.talkevents.cassandra.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public User create(User user) {
        user.setId(UUID.randomUUID());

        if (user.getAddressId() != null && !addressRepository.existsById(user.getAddressId())) {
            throw new RuntimeException("Address not found");
        }

        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public User update(UUID id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }

        if (user.getAddressId() != null && !addressRepository.existsById(user.getAddressId())) {
            throw new RuntimeException("Address not found");
        }

        existingUser.setName(user.getName());
        existingUser.setAge(user.getAge());
        existingUser.setEmail(user.getEmail());
        existingUser.setAddressId(user.getAddressId());

        return userRepository.save(existingUser);
    }

    public void delete(UUID id) {
        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(id);
    }
}