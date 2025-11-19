package it.corso.mygym.controller;

import it.corso.mygym.model.dto.UserDto;
import it.corso.mygym.model.dto.UserRequest;
import it.corso.mygym.model.exception.UserNotFoundException;
import it.corso.mygym.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    @PostMapping(

    )
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserRequest userRequest) {
        UserDto userSaved = userService.save(userRequest);

        // TODO: create UserSuccessResponse
        // TODO: add metadata
        // TODO: add status

        // TODO: return complete response
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UserRequest userRequest) throws UserNotFoundException {
        UserDto userUpdated = userService.update(id, userRequest);

        return ResponseEntity.ok(userUpdated);
    }

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<?> userNotFound(RuntimeException e){

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
