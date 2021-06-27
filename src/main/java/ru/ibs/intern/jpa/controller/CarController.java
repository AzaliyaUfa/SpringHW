package ru.ibs.intern.jpa.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ibs.intern.jpa.entities.*;
import ru.ibs.intern.jpa.repo.*;
import ru.ibs.intern.jpa.responses.Response;
import ru.ibs.intern.jpa.service.serviceImpl.CarServiceImpl;

import java.util.List;


@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarServiceImpl carService;


    /*{"manufacturerName": "ford",
            "modelName": "focus"}

            //////

            {"manufacturerName": "lada",
            "modelName": "kalina",
            "engine": {
	                "type": "petrol"
            }}*/

    /// CREATE ///

    @PostMapping(value = "create", consumes = {MediaType.APPLICATION_JSON_VALUE},
                                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }

    /// READ ///

    @GetMapping(value ={"read","read/","read/{id}"})
    public List<Car> readCar(@PathVariable(name = "id", required = false) Long id ) {
        if (id != null) {
            return carService.getById(id);
        } else {
            return carRepository.findAll();
        }
    }

    /// UPDATE ///

    @PostMapping(value ={"update","update/{id}"}, consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response updateCar(@RequestBody Car car, @PathVariable(name = "id", required = false) Long id) {
        return carService.updateCar(id, car);
    }

    /// DELETE ///

    @PostMapping(value ={"delete","delete/{id}"})
    public Response deleteCar(@PathVariable(name = "id", required = false) Long id) throws Exception {
        return carService.deleteCar(id);
    }

}
