package it.corso.mygym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.corso.mygym.model.User;
import it.corso.mygym.model.dto.UserDto;
import it.corso.mygym.model.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("/users")
public interface UserController {



    @Operation(
            summary = "Create a new user record",
            description = "Use this resource to add a new user record.",
            tags = {"user-service"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User record created.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad or malformed request"),
            @ApiResponse(responseCode = "403", description = "User is not entitled to create this User record."),
            @ApiResponse(responseCode = "412", description = "User record creation failed, due to validations."),
            @ApiResponse(responseCode = "500", description = "An unexpected error has occurred. User record was not created.")})
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<User> save(@Valid @RequestBody UserDto userDto);


    @Operation(
            summary = "Update a user record",
            description = "Use this resource to update an existing user record in the repository.",
            tags = {"user-service"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User record updated.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad or malformed request"),
            @ApiResponse(responseCode = "403", description = "User is not entitled to update this User record."),
            @ApiResponse(responseCode = "404", description = "User record was not found."),
            @ApiResponse(responseCode = "412", description = "User record record update failed."),
            @ApiResponse(responseCode = "500", description = "An unexpected error has occurred. User record was not updated.")})
    @PutMapping("/{id}")
    ResponseEntity<User> update(@PathVariable(value = "id") Long id,
                                @Valid @RequestBody UserDto userDto) throws UserNotFoundException;
}
