package it.corso.mygym.service;

import it.corso.mygym.model.dto.UserDto;
import it.corso.mygym.model.dto.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDto save(UserRequest userRequest);

    UserDto findById(Long id);

    List<UserDto> findAll(boolean includeInactiveFlag);

    UserDto update(Long id, UserRequest userRequest);

    UserDto deleteById(Long id);
}
