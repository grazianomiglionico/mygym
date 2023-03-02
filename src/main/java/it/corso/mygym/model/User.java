package it.corso.mygym.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
@JsonInclude(JsonInclude.Include.NON_NULL) //The @JsonInclude annotation provides hints about what content to serialize. In this example, all non-null values are serialized
@JsonPropertyOrder({
        "id",
        "name",
        "surname",
        "email",
        "activeFlag"
})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String name;

    @Column(length = 30)
    private String surname;

    @Column(name = "email", length = 50, unique = true)
    private String email;

    @NotNull
    private boolean activeFlag = true;

    @OneToMany(mappedBy = "user")
    private List<Subscription> subscriptions;
}
