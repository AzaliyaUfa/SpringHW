package ru.ibs.intern.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ibs.intern.jpa.entities.Car;
import ru.ibs.intern.jpa.entities.Engine;

import java.util.List;


public interface EngineRepository extends JpaRepository<Engine, Long> {

    List<Engine> findAllById(Long id);

}
