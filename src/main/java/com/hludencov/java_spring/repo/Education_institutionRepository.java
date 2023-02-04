package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.Education_institution;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Education_institutionRepository extends CrudRepository<Education_institution, Long> {
    List<Education_institution> findByNameContains(String name);
    List<Education_institution> findByName(String title);
}