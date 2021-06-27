package ru.ibs.intern.jpa.service.serviceImpl;


import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ibs.intern.jpa.entities.*;
import ru.ibs.intern.jpa.exceptions.NoElementException;
import ru.ibs.intern.jpa.exceptions.NoIdException;
import ru.ibs.intern.jpa.repo.CarRepository;
import ru.ibs.intern.jpa.responses.Response;
import ru.ibs.intern.jpa.service.interfaces.CarService;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car addCar(String mnfName, String modelName, String engineType) {
        final Car newCar = new Car(mnfName, modelName);

        final SteeringWheel steeringWheel = new SteeringWheel(String.join(" ", newCar.getManufacturerName(),newCar.getModelName(),"steering wheel"));
        newCar.setSteeringWheel(steeringWheel);

        Engine engine = new Engine(engineType);
        for (int i = 5; i <= 10; i++) {
            final Gear gear = new Gear(i);
            gear.setEngine(engine);
            engine.getGears().add(gear);
        }
        newCar.setEngine(engine);
        Manual cylinderHeadManual = new Manual("Cylinder head manual for " + engineType);
        engine.getManuals().add(cylinderHeadManual);
        cylinderHeadManual.getEngines().add(engine);
        Manual electricManual = new Manual("Electrics manual for " + engineType);
        engine.getManuals().add(electricManual);
        electricManual.getEngines().add(engine);

        return carRepository.save(newCar);
    }

    public Response createCar(Car car) {
        Car newCar;
        if (car == null) {
            throw new ResourceNotFoundException("Empty", new Throwable());
        } else if (car.getManufacturerName() != null && car.getModelName() != null
                && car.getEngine() != null &&  car.getEngine().getType() != null) {
            newCar = addCar(car.getManufacturerName(), car.getModelName(), car.getEngine().getType());
        } else {
            newCar = carRepository.save(car);
        }
        return new Response(newCar.getId(), "Car", "created");
    }

    public List<Car> getById(Long id) {
        List<Car> carList = carRepository.findAllById(id);
        if(carList.isEmpty() && id == null) {
            throw new NoIdException();
        } else if (carList.isEmpty()) {
            throw new NoElementException("Car with id = " + id + " is not found.");
        }
        return carList;
    }

    public Response updateCar(Long id, Car car) {
        getById(id);
        car.setId(id);
        carRepository.save(car);
        return new Response(id, "car", "updated");
    }

    public Response deleteCar(Long id) {
        getById(id);
        carRepository.deleteById(id);
        return new Response(id, "car", "deleted");
    }


}
