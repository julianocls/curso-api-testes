package br.com.dicasdeumdev.api.service;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    User create(UserDto obj);

    User update(UserDto obj);

    void delete(Integer id);

}
