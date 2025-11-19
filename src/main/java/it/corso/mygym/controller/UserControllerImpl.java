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
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserRequest userRequest) {
        UserDto userSaved = userService.save(userRequest);

        // TODO: create UserSuccessResponse
        // TODO: add metadata
        // TODO: add status

        // TODO: return complete response
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDto> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UserRequest userRequest) throws UserNotFoundException {
        UserDto userUpdated = userService.update(id, userRequest);

        return ResponseEntity.ok(userUpdated);
    }

    @Override
    public ResponseEntity<UserDto> getAll() {
        return userService.findAll(false).stream()
                .map(ResponseEntity::ok)
                .findFirst()
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<UserDto> getById(Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Override
    public ResponseEntity<UserDto> deleteById(Long id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<?> userNotFound(RuntimeException e){

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
