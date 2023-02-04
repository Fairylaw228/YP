package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.Subject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
    List<Subject> findByNameContains(String name);
    List<Subject> findByName(String title);
}
