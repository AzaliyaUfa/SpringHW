package ru.ibs.intern.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ibs.intern.jpa.entities.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {


    List<Car> findAllById(Long id);

}
