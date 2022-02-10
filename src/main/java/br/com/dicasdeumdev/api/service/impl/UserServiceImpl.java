package br.com.dicasdeumdev.api.service.impl;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDto;
import br.com.dicasdeumdev.api.repository.UserRepository;
import br.com.dicasdeumdev.api.service.UserService;
import br.com.dicasdeumdev.api.service.exception.EmailExistenteFoundException;
import br.com.dicasdeumdev.api.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado para o id ["+id+"]!"));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User create(UserDto obj) throws EmailExistenteFoundException {

        Optional<User> userOptional = findByEmail(obj.getEmail());
        if (userOptional.isPresent()) {
           throw new EmailExistenteFoundException("Email já cadastrado para o usuário ["+userOptional.get().getNome()+"] !");
        }

        return repository.save(mapper.map(obj, User.class));
    }


}
