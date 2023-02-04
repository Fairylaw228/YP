package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.Candidate_info;
import com.hludencov.java_spring.models.Document;
import com.hludencov.java_spring.models.Summary;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SummaryRepository extends CrudRepository<Summary, Long> {
    List<Summary> findByMark(int title);
    List<Summary> findByDocument(Document document);

}
