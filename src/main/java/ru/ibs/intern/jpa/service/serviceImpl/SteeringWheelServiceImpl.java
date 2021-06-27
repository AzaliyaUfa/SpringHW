package ru.ibs.intern.jpa.service.serviceImpl;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.ibs.intern.jpa.entities.SteeringWheel;
import ru.ibs.intern.jpa.exceptions.LinkedItemException;
import ru.ibs.intern.jpa.exceptions.NoElementException;
import ru.ibs.intern.jpa.exceptions.NoIdException;
import ru.ibs.intern.jpa.repo.SteeringWheelRepository;
import ru.ibs.intern.jpa.responses.Response;
import ru.ibs.intern.jpa.service.interfaces.SteeringWheelService;

import java.util.List;

@Service
public class SteeringWheelServiceImpl implements SteeringWheelService {

    @Autowired
    private SteeringWheelRepository swRepository;

    public Response createSteeringWheel(SteeringWheel sw) {
        SteeringWheel newSteeringWheel;
        if (sw == null) {
            throw new ResourceNotFoundException("Empty", new Throwable());
        } else {
            newSteeringWheel = swRepository.save(sw);
        }
        return new Response(newSteeringWheel.getId(), "Steering wheel", "created");
    }

    public List<SteeringWheel> getById(Long id) {
        List<SteeringWheel> steeringWheelList = swRepository.findAllById(id);
        if(steeringWheelList.isEmpty() && id == null) {
            throw new NoIdException();
        } else if (steeringWheelList.isEmpty()) {
            throw new NoElementException("Steering wheel with id = " + id + " is not found.");
        }
        return steeringWheelList;
    }

    public Response updateSteeringWheel(Long id, SteeringWheel sw) {
        getById(id);
        sw.setId(id);
        swRepository.save(sw);
        return new Response(id, "Steering wheel", "updated");
    }


    public Response deleteSteeringWheel(Long id) {
        getById(id);
        try{
            swRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException ex) {
            throw new LinkedItemException();
        }
        return new Response(id, "Steering wheel", "deleted");
    }

}
