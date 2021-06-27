package ru.ibs.intern.jpa.service.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ibs.intern.jpa.entities.*;
import ru.ibs.intern.jpa.exceptions.NoElementException;
import ru.ibs.intern.jpa.exceptions.NoIdException;
import ru.ibs.intern.jpa.repo.*;
import ru.ibs.intern.jpa.service.CarServiceImpl;

import java.util.List;


@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private ObjectMapper objectMapper;


    /*{"manufacturerName": "ford",
            "modelName": "focus"}

            //////

            {"manufacturerName": "lada",
            "modelName": "kalina",
            "engine": {
	                "type": "petrol"
            }}*/

    /// CREATE ///

    @PostMapping(value = "create", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Response createCar(@RequestBody Car car) {
        if (car.getManufacturerName() != null && car.getModelName() != null && car.getEngine().getType() != null) {
            carService.addCar(car.getManufacturerName(), car.getModelName(), car.getEngine().getType());
        } else {
            carRepository.save(car);
        }
        return new Response("");
    }


    /// READ ///

    @GetMapping(value ={"read/","read/{id}"})
    public List<Car> readCar(@PathVariable(name = "id", required = false) Long id ) {
        if (!carRepository.findAllById(id).isEmpty()) {
            return carRepository.findAllById(id);
        } else if(id != null) {
            System.out.println("Car with id = " + id + " is not found.");
            return null;
        } else {
            return carRepository.findAll();
        }
    }

    /// UPDATE ///

    @PostMapping(value ={"update","update/{id}"}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void updateGear(@RequestBody Car car, @PathVariable(name = "id", required = false) Long id) throws Exception {

        List<Car> carList = carService.getById(id);
        car.setId(id);
        carRepository.save(car);
        System.out.println("Car with id = " + id + " was updated.");
    }

    /// DELETE ///

    @PostMapping(value ={"delete/","delete/{id}"})
    public void deleteCar(@PathVariable(name = "id", required = false) Long id) throws Exception {
        System.out.println(id);
        if (!carRepository.findAllById(id).isEmpty()) {
            carRepository.deleteById(id);
            System.out.println("Car with id = " + id + " was deleted.");
        } else if(id != null) {
            System.out.println("Car with id = " + id + " is not found.");
        } else {
            throw new Exception("Empty id!");
        }
    }

}
