package com.hu.microserviceuser.domain.service;

import com.hu.microserviceuser.data.entity.User;
import com.hu.microserviceuser.data.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnAllUsers() {
        List<User> datas = new ArrayList<>();
        datas.add(new User(UUID.fromString("a9f7b062-1f80-11eb-adc1-0242ac120002"),"username1","firstname1","lastname1"));
        datas.add(new User(UUID.fromString("c8bf5810-1f80-11eb-adc1-0242ac120002"),"username2","firstname2","lastname2"));
        datas.add(new User(UUID.fromString("d2230730-1f80-11eb-adc1-0242ac120002"),"username3","firstname3","lastname3"));
        datas.add(new User(UUID.fromString("d8567f4c-1f80-11eb-adc1-0242ac120002"),"username4","firstname4","lastname4"));
        given(userRepository.findAll()).willReturn(datas);
        List<User> expected = userService.findAll();
        assertEquals(expected,datas);
    }

    @Test
    void shouldDelete() {
        final UUID userId = UUID.fromString("d8567f4c-1f80-11eb-adc1-0242ac120002");
        userService.deleteUser(userId);
        userService.deleteUser(userId);
        verify(userRepository, times(2)).deleteById(userId);
    }

    @Test
    void shouldReturnOneUser() {
        final UUID id = UUID.fromString("d8567f4c-1f80-11eb-adc1-0242ac120002");
        final User user = new User(id,"username1","firstname1","lastname1");
        given(userRepository.findById(id)).willReturn(Optional.of(user));
        final User expected = userService.findById(id);
        assertThat(expected).isNotNull();
    }
}