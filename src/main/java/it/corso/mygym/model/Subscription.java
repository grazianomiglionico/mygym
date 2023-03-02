package it.corso.mygym.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import it.corso.mygym.model.enums.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "subscription")
@JsonInclude(JsonInclude.Include.NON_NULL) //The @JsonInclude annotation provides hints about what content to serialize. In this example, all non-null values are serialized
@JsonPropertyOrder({
        "id",
        "name",
        "price",
        "startDate",
        "endDate",
        "paid"
})
public class Subscription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private boolean paid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference() // to resolve JSON infinite recursion problem
    private User user;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    @JsonBackReference() // to resolve JSON infinite recursion problem
    private Gym gym;

    @Column(name = "type")
    private Type type;
}
