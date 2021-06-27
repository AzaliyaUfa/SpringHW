package ru.ibs.intern.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Engine {

    @Id @GeneratedValue
    private Long id;

    private String type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "engine")
    private List<Gear> gears;

    @JsonIgnore
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
