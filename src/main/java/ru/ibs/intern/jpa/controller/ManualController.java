package ru.ibs.intern.jpa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ibs.intern.jpa.entities.Manual;
import ru.ibs.intern.jpa.repo.ManualRepository;
import ru.ibs.intern.jpa.responses.Response;
import ru.ibs.intern.jpa.service.serviceImpl.ManualServiceImpl;

import java.util.List;


@RestController
@RequestMapping("/api/manual")
public class ManualController {

    @Autowired
    private ManualRepository manualRepository;
    @Autowired
    private ManualServiceImpl manualService;


    // {"type": "Cylinder head manual for petrol"}

    /// CREATE ///

    @PostMapping(value = "create", consumes = {MediaType.APPLICATION_JSON_VALUE},
                                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response createManual(@RequestBody Manual manual) {
        return manualService.createManual(manual);
    }

    /// READ ///

    @GetMapping(value ={"read","read/","read/{id}"})
    public List<Manual> readManual(@PathVariable(name = "id", required = false) Long id ) {
        if (id != null) {
            return manualService.getById(id);
        } else {
            return manualRepository.findAll();
        }
    }

    /// UPDATE ///

    @PostMapping(value ={"update","update/{id}"}, consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response updateManual(@RequestBody Manual manual, @PathVariable(name = "id", required = false) Long id) {
        return manualService.updateManual(id, manual);
    }

    /// DELETE ///

    @PostMapping(value ={"delete","delete/{id}"})
    public Response deleteManual(@PathVariable(name = "id", required = false) Long id) throws Exception {
        return manualService.deleteManual(id);
    }

}
