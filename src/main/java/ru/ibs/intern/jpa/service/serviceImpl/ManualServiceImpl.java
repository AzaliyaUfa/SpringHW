package ru.ibs.intern.jpa.service.serviceImpl;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.ibs.intern.jpa.entities.Manual;
import ru.ibs.intern.jpa.exceptions.LinkedItemException;
import ru.ibs.intern.jpa.exceptions.NoElementException;
import ru.ibs.intern.jpa.exceptions.NoIdException;
import ru.ibs.intern.jpa.repo.ManualRepository;
import ru.ibs.intern.jpa.responses.Response;
import ru.ibs.intern.jpa.service.interfaces.ManualService;

import java.util.List;

@Service
public class ManualServiceImpl implements ManualService {

    @Autowired
    private ManualRepository manualRepository;

    public Response createManual(Manual manual) {
        Manual newManual;
        if (manual == null) {
            throw new ResourceNotFoundException("Empty", new Throwable());
        } else {
            newManual = manualRepository.save(manual);
        }
        return new Response(newManual.getId(), "Manual", "created");
    }

    public List<Manual> getById(Long id) {
        List<Manual> manualList = manualRepository.findAllById(id);
        if(manualList.isEmpty() && id == null) {
            throw new NoIdException();
        } else if (manualList.isEmpty()) {
            throw new NoElementException("Manual with id = " + id + " is not found.");
        }
        return manualList;
    }

    public Response updateManual(Long id, Manual manual) {
        getById(id);
        manual.setId(id);
        manualRepository.save(manual);
        return new Response(id, "Manual", "updated");
    }


    public Response deleteManual(Long id) {
        getById(id);
        try{
            manualRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException ex) {
            throw new LinkedItemException();
        }
        return new Response(id, "Manual", "deleted");
    }

}
