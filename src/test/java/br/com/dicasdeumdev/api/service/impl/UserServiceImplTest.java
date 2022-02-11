package br.com.dicasdeumdev.api.service.impl;

import br.com.dicasdeumdev.api.builder.UserBuilder;
import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.repository.UserRepository;
import br.com.dicasdeumdev.api.service.exception.DataIntegratyViolationException;
import br.com.dicasdeumdev.api.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
    void whenFindByReturnObjectNotFoundException() {
        final String objectNotFound = "Objeto não encontrado para o id [" + user.getId() + "]!";

        // Scenery
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(objectNotFound));

        try {
            // Action
            User response = service.findById(user.getId());
        } catch (Exception ex) {
            // Verification
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(objectNotFound, ex.getMessage());
        }
    }

    @Test
    void whenFindAllReturnAnListOfUsers() {
        // Scenery
        User user1 = UserBuilder.anUser().withId(1).withNome("User 1").whitEmail("user1@email.com").now();
        User user2 = UserBuilder.anUser().withId(2).withNome("User 2").whitEmail("user2@email.com").now();
        User user3 = UserBuilder.anUser().withId(3).withNome("User 3").whitEmail("user3@email.com").now();
        List<User> userListReturn = Arrays.asList(user1, user2, user3);
        when(repository.findAll()).thenReturn(userListReturn);

        // Action
        List<User> respose = service.findAll();

        // Verification
        assertNotNull(respose);
        assertEquals(userListReturn.size(), respose.size());
        assertEquals(User.class, respose.get(0).getClass());
    }

    @Test
    void whenCreateReturnUserInstance() {
        // Scenery
        when(repository.save(any())).thenReturn(user);

        // Action
        User response = service.create(userDTO);

        // Verification
        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(user.getId(), response.getId());
        assertEquals(user.getNome(), response.getNome());
        assertEquals(user.getEmail(), response.getEmail());
    }

    @Test
    void whenCreateReturnDataIntegrityViolationException() {
        // Scenery
        when(repository.findByEmail(anyString())).thenReturn(userOptional);

        try {
            // Action
            userOptional.get().setId(99);
            User response = service.create(userDTO);
        } catch (Exception ex) {
            // Verification
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("Email já cadastrado para o usuário [" + userOptional.get().getNome() + "] !", ex.getMessage());
        }
    }

    @Test
    void whenUpdateReturnUserInstance() {
        // Scenery
        when(repository.save(any())).thenReturn(user);

        // Action
        User response = service.update(userDTO);

        // Verification
        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(user.getId(), response.getId());
        assertEquals(user.getNome(), response.getNome());
        assertEquals(user.getEmail(), response.getEmail());
    }

    @Test
    void whenUpdateReturnDataIntegrityViolationException() {
        // Scenery
        when(repository.findByEmail(anyString())).thenReturn(userOptional);

        try {
            // Action
            userOptional.get().setId(99);
            User response = service.update(userDTO);
        } catch (Exception ex) {
            // Verification
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("Email já cadastrado para o usuário [" + userOptional.get().getNome() + "] !", ex.getMessage());
        }
    }

    @Test
    void whenDeleteCheckIfUserNotExists() {
        // Scenery
        when(repository.findById(anyInt())).thenReturn(userOptional);

        // Action
        doNothing().when(repository).deleteById(anyInt());
        service.delete(user.getId());

        // Verification
        verify(repository, timeout(1)).deleteById(anyInt());
    }

    @Test
    void whenDeleteReturnObjectNotFoundException() {
        final String objectNotFound = "Objeto não encontrado para o id [" + user.getId() + "]!";

        // Scenery
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(objectNotFound));

        try {
            // Action
            service.delete(user.getId());
        } catch (Exception ex) {
            // Verification
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(objectNotFound, ex.getMessage());
        }
    }

    private void startUSer() {
        user = UserBuilder.anUser().now();
        userDTO = new UserDTO(user.getId(), user.getNome(), user.getEmail(), user.getPassword());
        userOptional = Optional.of(user);
    }
}