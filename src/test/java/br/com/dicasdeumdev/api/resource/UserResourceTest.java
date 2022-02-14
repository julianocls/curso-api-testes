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
import org.springframework.http.ResponseEntity;

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
    void whenFindByIdSuccess() {
        // Scenery
        when(service.findById(anyInt())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        // Action
        ResponseEntity<UserDTO> response = resource.findById(anyInt());

        // Verifications
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(UserDTO.class, response.getBody().getClass());
        Assertions.assertEquals(userDTO.getId(), response.getBody().getId());
        Assertions.assertEquals(userDTO.getEmail(), response.getBody().getEmail());
        Assertions.assertEquals(userDTO.getNome(), response.getBody().getNome());
        Assertions.assertEquals(userDTO.getPassword(), response.getBody().getPassword());
    }

    @Test
    void findAll() {
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