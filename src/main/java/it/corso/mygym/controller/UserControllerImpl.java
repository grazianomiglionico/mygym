package it.corso.mygym.controller;

import it.corso.mygym.dto.ApiResponse;
import it.corso.mygym.dto.UserDto;
import it.corso.mygym.dto.request.UserRequest;
import it.corso.mygym.exception.UserNotFoundException;
import it.corso.mygym.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<ApiResponse<UserDto>> save(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Utente creato con successo", userService.save(userRequest)));
    }

    @Override
    public ResponseEntity<ApiResponse<UserDto>> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UserRequest userRequest) throws UserNotFoundException {
        return ResponseEntity.ok(ApiResponse.ok(
                "Utente aggiornato con successo",
                userService.update(id, userRequest)));
    }

    @Override
    public ResponseEntity<ApiResponse<List<UserDto>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok(userService.findAll(false)));
    }

    @Override
    public ResponseEntity<ApiResponse<UserDto>> getById(Long id) {
        return ResponseEntity.ok(ApiResponse.ok(userService.findById(id)));
    }

    @Override
    public ResponseEntity<ApiResponse<UserDto>> deleteById(Long id) {
        return ResponseEntity.ok(ApiResponse.ok(userService.deleteById(id)));
    }
}
