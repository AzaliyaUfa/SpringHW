package ru.ibs.intern.jpa.service.interfaces;


import ru.ibs.intern.jpa.entities.Car;
import ru.ibs.intern.jpa.entities.Gear;

import java.util.List;

public interface GearSevice {

    Gear findGear(Car car);
    List<Gear> findGears(Car car);
}
