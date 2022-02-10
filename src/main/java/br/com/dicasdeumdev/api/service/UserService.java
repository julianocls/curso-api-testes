package br.com.dicasdeumdev.api.service;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDto;
import br.com.dicasdeumdev.api.service.exception.EmailExistenteFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    Optional<User> findByEmail(String email);

    User create(UserDto obj) throws EmailExistenteFoundException;

}
