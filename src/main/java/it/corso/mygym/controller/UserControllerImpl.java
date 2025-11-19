package it.corso.mygym.controller;

import it.corso.mygym.model.User;
import it.corso.mygym.model.dto.UserDto;
import it.corso.mygym.model.exception.UserNotFoundException;
import it.corso.mygym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

    private UserService userService;

    @Autowired
    public UserControllerImpl(final UserService userService) {this.userService = userService;}

    @Override
    @PostMapping(

    )
    public ResponseEntity<User> save(@Valid @RequestBody UserDto userDto) {
        User userSaved = userService.save(userDto);

        // TODO: create UserSuccessResponse
        // TODO: add metadata
        // TODO: add status

        // TODO: return complete response
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UserDto userDto) throws UserNotFoundException {
        User userUpdated = userService.update(id, userDto);

        return ResponseEntity.ok(userUpdated);
    }

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<?> userNotFound(RuntimeException e){

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
