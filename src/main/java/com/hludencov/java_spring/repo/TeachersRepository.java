package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.Teacher_info;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeachersRepository extends CrudRepository<Teacher_info, Long> {
    List<Teacher_info> findByTeachSinceContains(String title);
}
