package it.corso.mygym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.corso.mygym.model.User;
import it.corso.mygym.model.dto.UserDto;
import it.corso.mygym.model.dto.UserRequest;
import it.corso.mygym.model.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Tag(name = "Users")
@RequestMapping("/users")
public interface UserController {



    @Operation(
            summary = "Create a new user record",
            description = "Use this resource to add a new user record."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User record created.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad or malformed request"),
            @ApiResponse(responseCode = "403", description = "User is not entitled to create this User record."),
            @ApiResponse(responseCode = "412", description = "User record creation failed, due to validations."),
            @ApiResponse(responseCode = "500", description = "An unexpected error has occurred. User record was not created.")})
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<UserDto> save(@Valid @RequestBody UserRequest userRequest);


    @Operation(
            summary = "Update a user record",
            description = "Use this resource to update an existing user record in the repository."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User record updated.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad or malformed request"),
            @ApiResponse(responseCode = "403", description = "User is not entitled to update this User record."),
            @ApiResponse(responseCode = "404", description = "User record was not found."),
            @ApiResponse(responseCode = "412", description = "User record record update failed."),
            @ApiResponse(responseCode = "500", description = "An unexpected error has occurred. User record was not updated.")})
    @PutMapping("/{id}")
    ResponseEntity<UserDto> update(@PathVariable(value = "id") Long id,
                                @Valid @RequestBody UserRequest userRequest) throws UserNotFoundException;

    @Operation(
            summary = "Get all users",
            description = "Use this resource to get all users."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Users retrieved.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad or malformed request"),
            @ApiResponse(responseCode = "403", description = "User is not entitled to get this User record."),
            @ApiResponse(responseCode = "404", description = "No users found."),
            @ApiResponse(responseCode = "412", description = "Users retrieval failed."),
            @ApiResponse(responseCode = "500", description = "An unexpected error has occurred. Users were not retrieved.")
    })
    @GetMapping()
    ResponseEntity<UserDto> getAll();

    @Operation(
            summary = "Get a user by id",
            description = "Use this resource to get a user by id."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User retrieved.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad or malformed request"),
            @ApiResponse(responseCode = "403", description = "User is not entitled to get this User record."),
            @ApiResponse(responseCode = "404", description = "User record was not found."),
            @ApiResponse(responseCode = "412", description = "User retrieval failed."),
            @ApiResponse(responseCode = "500", description = "An unexpected error has occurred. User was not retrieved.")
    })
    @GetMapping("/{id}")
    ResponseEntity<UserDto> getById(@PathVariable(value = "id") Long id);

    @Operation(
            summary = "Delete a user by id",
            description = "Use this resource to delete a user by id."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User deleted.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad or malformed request"),
            @ApiResponse(responseCode = "403", description = "User is not entitled to delete this User record."),
            @ApiResponse(responseCode = "404", description = "User record was not found."),
            @ApiResponse(responseCode = "412", description = "User deletion failed."),
            @ApiResponse(responseCode = "500", description = "An unexpected error has occurred. User was not deleted.")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<UserDto> deleteById(@PathVariable(value = "id") Long id);
}
