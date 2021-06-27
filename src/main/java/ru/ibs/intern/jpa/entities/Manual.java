package ru.ibs.intern.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Manual {

    @Id
    @GeneratedValue
    private Long id;

    String type;

    @JsonIgnoreProperties("expenses")
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "manuals")
    List<Engine> engines;

    public Manual(String type) {
        this.type  = type;
        this.engines = new LinkedList<>();
    }
}
