package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
        User findByLogin(String title);
}
