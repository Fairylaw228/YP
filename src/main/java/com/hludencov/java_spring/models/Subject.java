package com.hludencov.java_spring.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject {

    public Subject(Long id, String subject_code, String name, Set<Teacher_info> teacher_infoList) {
        this.id = id;
        this.subject_code = subject_code;
        this.name = name;
        this.teacher_infoList = teacher_infoList;
    }

    public Subject() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    public String subject_code;
    @NotBlank
    public String name;

    @ManyToMany
    @JoinTable(name = "teacher_to_subject",
            joinColumns = @JoinColumn(name = "teacher_info_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    public Set<Teacher_info> teacher_infoList;

    @ManyToMany
    @JoinTable(name = "prep_to_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "preparation_program_id"))
    public Set<PreparationProgram> preparation_programs;

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private Set<Summary> summaries;


    //______________________________BOILERPLATE LINE__________________________________
    public Set<PreparationProgram> getPreparation_programs() {
        return preparation_programs;
    }

    public void setPreparation_programs(Set<PreparationProgram> preparation_programs) {
        this.preparation_programs = preparation_programs;
    }

    public Set<Summary> getSummaries() {
        return summaries;
    }

    public void setSummaries(Set<Summary> summaries) {
        this.summaries = summaries;
    }

    public Set<Teacher_info> getTeacher_infoList() {
        return teacher_infoList;
    }

    public void setTeacher_infoList(Set<Teacher_info> teacher_infoList) {
        this.teacher_infoList = teacher_infoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
