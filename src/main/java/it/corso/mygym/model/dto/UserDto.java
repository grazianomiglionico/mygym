package it.corso.mygym.model.dto;

import java.io.Serializable;

/**
 * DTO for {@link it.corso.mygym.model.User}
 */
public record UserDto(Long id, String name, String surname, String email, boolean activeFlag) implements Serializable {
}