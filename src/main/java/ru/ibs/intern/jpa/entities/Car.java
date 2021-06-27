package ru.ibs.intern.jpa.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String manufacturerName;

    @NotNull
    private String modelName;

    @JsonIgnoreProperties("expenses")
    @OneToOne(cascade = CascadeType.ALL)
    private Engine engine;

    @JsonIgnoreProperties("expenses")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ENGINEEEE_ID")
    private SteeringWheel steeringWheel;

    public Car(String manufacturerName, String modelName) {
        this.manufacturerName = manufacturerName;
        this.modelName = modelName;
    }

    public String toString() {
        return String.join(" ", manufacturerName, modelName);
    }
}
