package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.Candidate_info;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CandidateRepository extends CrudRepository<Candidate_info, Long> {
    List<Candidate_info> findBySubmissionDateContains(String name);
}
