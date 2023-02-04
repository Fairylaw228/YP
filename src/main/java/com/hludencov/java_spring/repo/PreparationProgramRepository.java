package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.PreparationProgram;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PreparationProgramRepository extends CrudRepository<PreparationProgram, Long> {
    List<PreparationProgram> findByNameContains(String name);
    List<PreparationProgram> findByName(String title);
}
