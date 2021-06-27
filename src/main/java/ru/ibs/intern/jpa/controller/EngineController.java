package ru.ibs.intern.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ibs.intern.jpa.entities.Engine;
import ru.ibs.intern.jpa.repo.EngineRepository;
import ru.ibs.intern.jpa.responses.Response;
import ru.ibs.intern.jpa.service.serviceImpl.EngineServiceImpl;

import java.util.List;


@RestController
@RequestMapping("/api/engine")
public class EngineController {

    @Autowired
    private EngineRepository engineRepository;

    @Autowired
    private EngineServiceImpl engineService;


    /*      {"type": "petrol"}

            //////   gears без id двигателя

            {"type": "petrol",
            "gears":[
            {"gearSize": 5},
            {"gearSize": 10}
            ]}
     */


    /// CREATE ///

    @PostMapping(value = "create", consumes = {MediaType.APPLICATION_JSON_VALUE},
                                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response createEngine(@RequestBody Engine engine) {
        return engineService.createEngine(engine);
    }

    /// READ ///

    @GetMapping(value ={"read","read/","read/{id}"})
    public List<Engine> readEngine(@PathVariable(name = "id", required = false) Long id ) {
        if (id != null) {
            return engineService.getById(id);
        } else {
            return engineRepository.findAll();
        }
    }

    /// UPDATE ///  {"type": "electrical"}

    @PostMapping(value ={"update","update/{id}"}, consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response updateEngine(@RequestBody Engine engine, @PathVariable(name = "id", required = false) Long id) {
        return engineService.updateEngine(id, engine);
    }

    /// DELETE ///

    @PostMapping(value ={"delete","delete/{id}"})
    public Response deleteEngine(@PathVariable(name = "id", required = false) Long id) throws Exception {
        return engineService.deleteEngine(id);
    }

}
