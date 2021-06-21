package ru.ibs.intern.jpa.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Engine {

    @Id @GeneratedValue
    private Long id;

    private String type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "engine")
    private List<Gear> gears;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Manual> manuals;

    public Engine(String engineType) {
        this.type = engineType;
        this.gears = new LinkedList<>();
        this.manuals = new LinkedList<>();
    }

    public String toString() {
        return type;
    }
}
