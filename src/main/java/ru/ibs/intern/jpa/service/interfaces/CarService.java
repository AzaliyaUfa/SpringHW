package ru.ibs.intern.jpa.service.interfaces;


import ru.ibs.intern.jpa.entities.Car;

public interface CarService {

    Car addCar(String mnfName, String modelName, String engineType);
}
