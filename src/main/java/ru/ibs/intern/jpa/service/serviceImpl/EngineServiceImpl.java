package ru.ibs.intern.jpa.service.serviceImpl;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ibs.intern.jpa.entities.Car;
import ru.ibs.intern.jpa.entities.Engine;
import ru.ibs.intern.jpa.exceptions.NoElementException;
import ru.ibs.intern.jpa.exceptions.NoIdException;
import ru.ibs.intern.jpa.repo.EngineRepository;
import ru.ibs.intern.jpa.responses.Response;
import ru.ibs.intern.jpa.service.interfaces.EngineService;

import java.util.List;

@Service
public class EngineServiceImpl implements EngineService {

    @Autowired
    private EngineRepository engineRepository;

    public Response createEngine(Engine engine) {
        Engine newEngine;
        if (engine == null) {
            throw new ResourceNotFoundException("Empty", new Throwable());
        } else {
            newEngine = engineRepository.save(engine);
        }
        return new Response(newEngine.getId(), "Engine", "created");
    }

    public List<Engine> getById(Long id) {
        List<Engine> engineList = engineRepository.findAllById(id);
        if(engineList.isEmpty() && id == null) {
            throw new NoIdException();
        } else if (engineList.isEmpty()) {
            throw new NoElementException("Car with id = " + id + " is not found.");
        }
        return engineList;
    }

    public Response updateEngine(Long id, Engine engine) {
        getById(id);
        engine.setId(id);
        engineRepository.save(engine);
        return new Response(id, "engine", "updated");
    }


    public Response deleteEngine(Long id) {
        getById(id);
        engineRepository.deleteById(id);
        return new Response(id, "engine", "deleted");
    }

}
