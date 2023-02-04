package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.Personal_info;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Personal_infoRepository extends CrudRepository<Personal_info, Long> {
    List<Personal_info> findByNameContains(String name);
    List<Personal_info> findByName(String title);
}