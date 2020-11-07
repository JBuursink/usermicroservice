package com.hu.microserviceuser.web.controller;

import com.hu.microserviceuser.data.entity.User;
import com.hu.microserviceuser.domain.service.UserService;
import com.hu.microserviceuser.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    private final ModelMapper modelMapper;

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable("id") UUID id) {
        return modelMapper.map(userService.findById(id), UserDto.class);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody User user) {
        final var savedUser = userService.createUser(user);
        return ResponseEntity.created(URI.create("/" + savedUser.getId())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.accepted().body(id);
    }

}
