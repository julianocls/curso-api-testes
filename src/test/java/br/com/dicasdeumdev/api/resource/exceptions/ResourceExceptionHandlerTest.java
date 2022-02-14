package br.com.dicasdeumdev.api.resource.exceptions;

import br.com.dicasdeumdev.api.service.exception.DataIntegratyViolationException;
import br.com.dicasdeumdev.api.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.swing.text.html.parser.Entity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {

    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado!";
    public static final String E_MAIL_JA_CADASTRADO = "E-Mail já cadastrado!";

    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void objectNotFound() {
        // Scenery

        // Action
        ResponseEntity<StandardError> response = exceptionHandler.objectNotFound(
                new ObjectNotFoundException("Objeto não encontrado!"),
                new MockHttpServletRequest()
        );

        // Verifications
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getTimestamp());
        assertNotNull(response.getBody().getPath());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void emailJaCadastrado() {
        // Scenery

        // Action
        ResponseEntity<StandardError> response = exceptionHandler.emailExistente(
                new DataIntegratyViolationException(E_MAIL_JA_CADASTRADO),
                new MockHttpServletRequest()
        );

        // Verifications
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getTimestamp());
        assertNotNull(response.getBody().getPath());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("E-Mail já cadastrado!", response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }

    @Test
    void emailExistente() {
    }
}