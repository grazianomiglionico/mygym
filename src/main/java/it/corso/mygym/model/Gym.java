package it.corso.mygym.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "gym")
@JsonInclude(JsonInclude.Include.NON_NULL) //The @JsonInclude annotation provides hints about what content to serialize. In this example, all non-null values are serialized
@JsonPropertyOrder({
        "id",
        "name",
        "address",
        "lat",
        "lon"
})
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @NotNull
    private Double lat;

    @NotNull
    private Double lon;

    @OneToMany(mappedBy = "gym")
    private List<Subscription> subscriptions;
}
