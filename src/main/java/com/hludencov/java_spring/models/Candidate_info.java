package com.hludencov.java_spring.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.Set;


@Entity
@Table(name = "candidate_info")
public class Candidate_info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @PastOrPresent
    public Date submissionDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "prepatation_program_id")
    public PreparationProgram preparationProgram;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    public User user;

    public double averageMark;

    @OneToMany(mappedBy = "candidate_info", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public Set<Summary> summaries;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "education_institution_id")
    private Education_institution education_institution;

    //______________________________BOILERPLATE LINE__________________________________


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PreparationProgram getPreparationProgram() {
        return preparationProgram;
    }

    public void setPreparationProgram(PreparationProgram preparationProgram) {
        this.preparationProgram = preparationProgram;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submission_date) {
        this.submissionDate = submission_date;
    }


    public Set<Summary> getSummaries() {
        return summaries;
    }

    public void setSummaries(Set<Summary> summaries) {
        this.summaries = summaries;
    }

    public Education_institution getEducation_institution() {
        return education_institution;
    }

    public void setEducation_institution(Education_institution education_institution) {
        this.education_institution = education_institution;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }
}
