package ru.ibs.intern.jpa;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ibs.intern.jpa.entities.*;
import ru.ibs.intern.jpa.repo.*;
import ru.ibs.intern.jpa.service.CarServiceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ControllerSpringHW {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarServiceImpl carService;
    @Autowired
    private GearRepository gearRepository;
    @Autowired
    private EngineRepository engineRepository;
    @Autowired
    private SteeringWheelRepository steeringWheelRepository;
    @Autowired
    private ManualRepository manualRepository;
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

    @PostMapping(value = "car/create", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void createCar(@RequestBody Car car) {
        if (car.getManufacturerName() != null && car.getModelName() != null && car.getEngine().getType() != null) {
            carService.addCar(car.getManufacturerName(), car.getModelName(), car.getEngine().getType());
        } else {
            carRepository.save(car);
        }
    }


    // {"type": "electrical"}
    @PostMapping(value = "engine/create", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Engine createCar(@RequestBody Engine engine) {
        engineRepository.save(engine);
        return engine;
    }

    // {"gearSize": 10}
    @PostMapping(value = "gear/create", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void createGear(@RequestBody Gear gear) {
        gearRepository.save(gear);
    }

    @PostMapping(value = "steeringWheel/create", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void createSteeringWheel(@RequestBody SteeringWheel steeringWheel) {
        steeringWheelRepository.save(steeringWheel);
    }

    @PostMapping(value = "manual/create", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void createManual(@RequestBody Manual manual) {
        manualRepository.save(manual);
    }


    /// READ ///


    @GetMapping("car/read")
    public List<Car> retrieveAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("car/read/{id}")
    public Car getCarById(@PathVariable Long id) {
        Optional<Car> car = carRepository.findById(id);
        return car.get();
    }


    @GetMapping("gear/read")
    public List<Gear> retrieveAllGears() {
        return gearRepository.findAll();
    }

    @GetMapping("gear/read/{id}")
    public Gear getGearById(@PathVariable Long id) throws Exception {
        try {
            Optional<Gear> gear = gearRepository.findById(id);
            return gear.get();
        } catch (Exception e) {
            throw new Exception("No gear with id = " + id);
        }
    }


    /// UPDATE ///


    @PostMapping(value ={"gear/update/","gear/update/{id}"}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void updateGear(@RequestBody Gear gear, @RequestParam(name = "id", required = false) Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Empty id!");
        }
        gear.setId(id);
        gearRepository.save(gear);
        System.out.println("Gear with id = " + id + " is updated.");
    }

    /*@PostMapping(value ={"gear/update/{id}"}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void updateGear(@RequestBody Gear gear, @RequestParam(name = "id") Long id) {
        try {
            Optional<Gear> gearOptional = gearRepository.findById(id);
        } catch (Exception e) {
            System.out.println("id is empty");
        }
        gear.setId(id);
        gearRepository.save(gear);
        System.out.println("Gear with id = " + id + " is updated.");
    }*/

    /// DELETE ///

    @PostMapping(value = "car/delete{id}")
    public void deleteCar(@PathVariable(name = "id") Long id) throws Exception {
        try {
            carRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Wrong ID.");
            throw new Exception("Ex message");
        }
    }

    @PostMapping(value = "engine/delete{id}")
    public void deleteEngine(@PathVariable(name = "id") Long id) throws Exception {
        try {
            engineRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Wrong ID.");
            throw new Exception("Ex message");
        }
    }

    @PostMapping("gear/delete/{id}")
    public void deleteGear(@PathVariable("id") Long id) throws Exception {
        try {
            gearRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Wrong ID.");
            throw new Exception("Ex message");
        }
    }

    @PostMapping("manual/delete/{id}")
    public void deleteManual(@PathVariable("id") Long id) throws Exception {
        try {
            manualRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Wrong ID.");
            throw new Exception("Ex message");
        }
    }

    @PostMapping("steeringWheel/delete/{id}")
    public void deleteSteeringWheel(@PathVariable("id") Long id) throws Exception {
        try {
            steeringWheelRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Wrong ID.");
            throw new Exception("Ex message");
        }
    }

}
