package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    List<Department> findByNameContains(String name);
    List<Department> findByName(String name);
}