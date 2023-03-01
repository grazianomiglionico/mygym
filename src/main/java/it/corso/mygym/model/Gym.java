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
@Table(name = "gym")
@JsonInclude(JsonInclude.Include.NON_NULL) //The @JsonInclude annotation provides hints about what content to serialize. In this example, all non-null values are serialized
@JsonPropertyOrder({
        "id",
        "name",
        "address",
        "lat",
        "lon"
})
public class Gym implements Serializable {

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
