package br.com.dicasdeumdev.api.service.impl;

import br.com.dicasdeumdev.api.builder.UserBuilder;
import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> userOptional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUSer();
    }

    @Test
    void whenFindByIdReturnUserInstance() {
        // scenery
        when(repository.findById(anyInt())).thenReturn(userOptional);

        // action
        User response = service.findById(user.getId());

        // verification
        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(user.getId(), response.getId());
        assertEquals(user.getNome(), response.getNome());
        assertEquals(user.getEmail(), response.getEmail());
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUSer() {
        user = UserBuilder.anUser().now();
        userDTO = new UserDTO(user.getId(), user.getNome(), user.getEmail(), user.getPassword());
        userOptional = Optional.of(user);
    }
}