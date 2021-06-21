package ru.ibs.intern.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ibs.intern.jpa.entities.Manual;

import java.util.List;

public interface ManualRepository extends JpaRepository<Manual, Long> {


}
