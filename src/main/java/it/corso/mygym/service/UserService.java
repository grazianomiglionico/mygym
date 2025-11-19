package it.corso.mygym.service;

import it.corso.mygym.model.User;
import it.corso.mygym.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User save(UserDto userDto);

    User findById(Long id);

    List<User> findAll(boolean includeInactiveFlag);

    User update(Long id, UserDto userDto);

    User deleteById(Long id);
}
