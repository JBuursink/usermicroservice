package com.hu.microserviceuser.domain.service;


import com.hu.microserviceuser.data.entity.User;
import com.hu.microserviceuser.data.repository.UserRepository;
import com.hu.microserviceuser.domain.exception.ResourceNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("User not found, id: %s", id),User.class));
    }
}
