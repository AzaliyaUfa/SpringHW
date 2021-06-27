package ru.ibs.intern.jpa.service.serviceImpl;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ibs.intern.jpa.entities.Car;
import ru.ibs.intern.jpa.entities.Gear;
import ru.ibs.intern.jpa.exceptions.NoElementException;
import ru.ibs.intern.jpa.exceptions.NoIdException;
import ru.ibs.intern.jpa.exceptions.NoSuchEngineException;
import ru.ibs.intern.jpa.repo.EngineRepository;
import ru.ibs.intern.jpa.repo.GearRepository;
import ru.ibs.intern.jpa.responses.Response;
import ru.ibs.intern.jpa.service.interfaces.GearSevice;

import java.util.List;

@Service
public class GearSeviceImpl implements GearSevice {

    @Autowired
    private GearRepository gearRepository;

    @Autowired
    private EngineRepository engineRepository;

    public List<Gear> getById(Long id) {
        List<Gear> gearList = gearRepository.findAllById(id);
        if(gearList.isEmpty() && id == null) {
            throw new NoIdException();
        } else if (gearList.isEmpty()) {
            throw new NoElementException("Gear with id = " + id + " is not found.");
        }
        return gearList;
    }

    public Response createGear(Gear gear) {
        Gear newGear;
        if (gear == null) {
            throw new ResourceNotFoundException("Empty", new Throwable());
        } else if (gear.getEngine() != null && gear.getEngine().getId() != null) {
            if(!engineRepository.findAllById(gear.getEngine().getId()).isEmpty()) {
                newGear = gearRepository.save(gear);
            } else {
                throw new NoSuchEngineException("Gear with wrong engine id!");
            }
        } else {
            newGear = gearRepository.save(gear);
        }
        return new Response(newGear.getId(), "Gear", "created");
    }

    public Response updateGear(Long id, Gear gear) {
        getById(id);
        if (gear == null) {
            throw new ResourceNotFoundException("Empty", new Throwable());
        } else if (gear.getEngine() != null && gear.getEngine().getId() != null) {
            if(!engineRepository.findAllById(gear.getEngine().getId()).isEmpty()) {
                gear.setId(id);
                gearRepository.save(gear);
            } else {
                throw new NoSuchEngineException("Gear with wrong or empty engine id!");
            }
        }
        return new Response(id, "gear", "updated");
    }

    public Response deleteGear(Long id) {
        getById(id);
        gearRepository.deleteById(id);
        return new Response(id, "gear", "deleted");
    }


    @Override
    public Gear findGear(Car car) {
        return gearRepository.findGearByEngineId(car.getEngine().getId());
    }

    @Override
    public List<Gear> findGears(Car car) {
        return gearRepository.findGearsByEngineId(car.getEngine().getId());
    }


}
