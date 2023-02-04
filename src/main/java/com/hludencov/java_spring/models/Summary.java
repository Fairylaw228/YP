package com.hludencov.java_spring.models;


import com.hludencov.java_spring.interfaces.IExelExport;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Summary implements IExelExport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Max(5)
    private int mark;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "candidate_info_id")
    private Candidate_info candidate_info;

    //______________________________BOILERPLATE LINE__________________________________

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Candidate_info getCandidate_info() {
        return candidate_info;
    }

    public void setCandidate_info(Candidate_info candidate_info) {
        this.candidate_info = candidate_info;
    }

    public List<Object> getHeaders() {
        return Arrays.asList(
                "Предмет",
                "Оценка",
                "Пользователь: " + candidate_info.user.getPersonal_info().name + " "
                        + candidate_info.user.getPersonal_info().getSec_name()
        );
    }
    public List<Object> getData() {
        return Arrays.asList(
                getSubject().getName(),
                getMark()
        );
    }
}
