package ru.ibs.intern.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ibs.intern.jpa.entities.Car;
import ru.ibs.intern.jpa.entities.SteeringWheel;

import java.util.List;

public interface SteeringWheelRepository extends JpaRepository<SteeringWheel, Long> {

    List<SteeringWheel> findAllById(Long id);

}
