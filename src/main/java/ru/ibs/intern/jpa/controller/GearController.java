package ru.ibs.intern.jpa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ibs.intern.jpa.entities.Gear;
import ru.ibs.intern.jpa.repo.GearRepository;
import ru.ibs.intern.jpa.responses.Response;
import ru.ibs.intern.jpa.service.serviceImpl.CarServiceImpl;
import ru.ibs.intern.jpa.service.serviceImpl.GearSeviceImpl;

import java.util.List;


@RestController
@RequestMapping("/api/gear")
public class GearController {

    @Autowired
    private GearRepository gearRepository;
    @Autowired
    private GearSeviceImpl gearService;


    /*{"gearSize": 14}

            //////

            {"gearSize": 13,
            "engine": {
                    "id": 11,
	                "type": "petrol"
            }}

            {"gearSize": 13,
            "engine": {
                    "id": 500,
	                "type": "petrol"
            }} */

    /// CREATE ///

    @PostMapping(value = "create", consumes = {MediaType.APPLICATION_JSON_VALUE},
                                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response createCar(@RequestBody Gear gear) {
        return gearService.createGear(gear);
    }

    /// READ ///

    @GetMapping(value ={"read","read/","read/{id}"})
    public List<Gear> readCar(@PathVariable(name = "id", required = false) Long id ) {
        if (id != null) {
            return gearService.getById(id);
        } else {
            return gearRepository.findAll();
        }
    }

    /// UPDATE ///

    @PostMapping(value ={"update","update/{id}"}, consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response updateCar(@RequestBody Gear gear, @PathVariable(name = "id", required = false) Long id) {
        return gearService.updateGear(id, gear);
    }

    /// DELETE ///

    @PostMapping(value ={"delete","delete/{id}"})
    public Response deleteCar(@PathVariable(name = "id", required = false) Long id) throws Exception {
        return gearService.deleteGear(id);
    }

}
