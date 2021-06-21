package ru.ibs.intern.jpa.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Gear {

    @Id
    @GeneratedValue
    private Long id;

    private int gearSize;

    @ManyToOne
    @JoinColumn(name = "ENGINE_ID", referencedColumnName = "ID")
    private Engine engine;

    public Gear(int size) {
        this.gearSize = size;
    }

    public String toString() {
        return "Gear for engine " + engine.toString() + " with size " + getGearSize();
    }
}
