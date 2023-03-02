package it.corso.mygym.controller;

import it.corso.mygym.model.User;
import it.corso.mygym.model.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

import static org.springframework.http.MediaType.*;

@RequestMapping("/users")
public interface UserController {


    @PostMapping(

    )
    ResponseEntity<User> save(@Valid @RequestBody UserDto userDto);
}
