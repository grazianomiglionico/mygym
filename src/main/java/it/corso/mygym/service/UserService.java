package it.corso.mygym.service;

import it.corso.mygym.model.User;
import it.corso.mygym.model.dto.UserDto;

import java.util.List;

public interface UserService {

    User save(User user);

    User findById(Long id);

    List<User> findAll(boolean includeInactiveFlag);

    User update(Long id, UserDto userDto);

    User deleteById(Long id);
}
