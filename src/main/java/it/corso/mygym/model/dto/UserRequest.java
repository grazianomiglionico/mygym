package it.corso.mygym.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRequest implements Serializable {

    private Long id;

    private String name;

    private String surname;

    private String email;
}
