package ru.ibs.intern.jpa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ibs.intern.jpa.entities.SteeringWheel;
import ru.ibs.intern.jpa.repo.SteeringWheelRepository;
import ru.ibs.intern.jpa.responses.Response;
import ru.ibs.intern.jpa.service.serviceImpl.SteeringWheelServiceImpl;

import java.util.List;


@RestController
@RequestMapping("/api/steeringWheel")
public class SteeringWheelController {

    @Autowired
    private SteeringWheelRepository swRepository;
    @Autowired
    private SteeringWheelServiceImpl swService;


    /*  {"type": "lada kalina steering wheel"}

            //////

*/

    /// CREATE ///

    @PostMapping(value = "create", consumes = {MediaType.APPLICATION_JSON_VALUE},
                                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response createSteeringWheel(@RequestBody SteeringWheel sw) {
        return swService.createSteeringWheel(sw);
    }

    /// READ ///

    @GetMapping(value ={"read","read/","read/{id}"})
    public List<SteeringWheel> readSteeringWheel(@PathVariable(name = "id", required = false) Long id ) {
        if (id != null) {
            return swService.getById(id);
        } else {
            return swRepository.findAll();
        }
    }

    /// UPDATE ///

    @PostMapping(value ={"update","update/{id}"}, consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response updateSteeringWheel(@RequestBody SteeringWheel sw, @PathVariable(name = "id", required = false) Long id) {
        return swService.updateSteeringWheel(id, sw);
    }

    /// DELETE ///

    @PostMapping(value ={"delete","delete/{id}"})
    public Response deleteSteeringWheel(@PathVariable(name = "id", required = false) Long id) {
        return swService.deleteSteeringWheel(id);
    }

}
