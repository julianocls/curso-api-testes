package br.com.dicasdeumdev.api.resource;

import br.com.dicasdeumdev.api.builder.UserBuilder;
import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class UserResourceTest {

    @InjectMocks
    UserResource resource;

    @Mock
    UserServiceImpl service;

    @Mock
    ModelMapper mapper;

    User user;
    UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUSer();
    }

    @Test
    void whenFindByIdReturnSuccess() {
        // Scenery
        when(service.findById(anyInt())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        // Action
        ResponseEntity<UserDTO> response = resource.findById(anyInt());

        // Verifications
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(UserDTO.class, response.getBody().getClass());
        Assertions.assertEquals(userDTO.getId(), response.getBody().getId());
        Assertions.assertEquals(userDTO.getEmail(), response.getBody().getEmail());
        Assertions.assertEquals(userDTO.getNome(), response.getBody().getNome());
        Assertions.assertEquals(userDTO.getPassword(), response.getBody().getPassword());
    }

    @Test
    void whenFindAllReturnListOfUsers() {
        User user1 = UserBuilder.anUser().withId(1).withNome("User 1").whitEmail("user1@email.com").now();
        User user2 = UserBuilder.anUser().withId(2).withNome("User 2").whitEmail("user2@email.com").now();
        User user3 = UserBuilder.anUser().withId(3).withNome("User 3").whitEmail("user3@email.com").now();

        // Scenary
        when(service.findAll()).thenReturn(Arrays.asList(user1, user2, user3));
        when(mapper.map(any(), any())).thenReturn(userDTO);

        // Action
        ResponseEntity<List<UserDTO>> response = resource.findAll();

        // Verifications
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(ArrayList.class, response.getBody().getClass());
        Assertions.assertEquals(UserDTO.class, response.getBody().get(0).getClass());
        Assertions.assertEquals(userDTO.getId(), response.getBody().get(0).getId());
        Assertions.assertEquals(userDTO.getEmail(), response.getBody().get(0).getEmail());
        Assertions.assertEquals(userDTO.getNome(), response.getBody().get(0).getNome());
        Assertions.assertEquals(userDTO.getPassword(), response.getBody().get(0).getPassword());
    }

    @Test
    void save() {
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
    }
}