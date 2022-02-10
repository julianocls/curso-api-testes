package br.com.dicasdeumdev.api.resource.exceptions;

import br.com.dicasdeumdev.api.service.exception.EmailExistenteFoundException;
import br.com.dicasdeumdev.api.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex, HttpServletRequest request) {
        StandardError erro = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(EmailExistenteFoundException.class)
    public ResponseEntity<StandardError> emailExistente(EmailExistenteFoundException ex, HttpServletRequest request) {
        StandardError erro = new StandardError(LocalDateTime.now(), HttpStatus.FOUND.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FOUND).body(erro);
    }
}
