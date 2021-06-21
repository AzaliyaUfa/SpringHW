package ru.ibs.intern.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.ibs.intern.jpa.entities.Gear;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GearRepository extends JpaRepository<Gear, Long> {

    Gear findGearByEngineId(Long engineId);
    List<Gear> findGearsByEngineId(Long engineId);

}
