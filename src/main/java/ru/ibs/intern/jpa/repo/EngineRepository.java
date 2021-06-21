package ru.ibs.intern.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ibs.intern.jpa.entities.Engine;


public interface EngineRepository extends JpaRepository<Engine, Long> {

}
