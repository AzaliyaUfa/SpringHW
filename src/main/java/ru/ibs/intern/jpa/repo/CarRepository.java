package ru.ibs.intern.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.ibs.intern.jpa.entities.Car;

public interface CarRepository extends JpaRepository<Car, Long> {


}
