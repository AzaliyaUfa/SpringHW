package ru.ibs.intern.jpa.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue
    private Long id;

    private String manufacturerName;

    private String modelName;

    @OneToOne(cascade = CascadeType.ALL)
    private Engine engine;

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
