package ru.ibs.intern.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.ibs.intern.jpa.entities.Car;
import ru.ibs.intern.jpa.entities.Gear;
import ru.ibs.intern.jpa.repo.GearRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GearSeviceImpl implements GearSevice {

    @Autowired
    private GearRepository gearRepository;

    @Override
    public Gear findGear(Car car) {
        return gearRepository.findGearByEngineId(car.getEngine().getId());
    }

    @Override
    public List<Gear> findGears(Car car) {
        return gearRepository.findGearsByEngineId(car.getEngine().getId());
    }


}
