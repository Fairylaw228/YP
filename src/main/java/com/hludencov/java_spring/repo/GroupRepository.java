package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.Group;
import com.hludencov.java_spring.models.PreparationProgram;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group, Long> {
    List<Group> findByPreparationProgram(PreparationProgram preparation_program);
}