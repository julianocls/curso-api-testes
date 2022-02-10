package br.com.dicasdeumdev.api.service;

import br.com.dicasdeumdev.api.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findById(Integer id);

}
