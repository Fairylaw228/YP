package com.hludencov.java_spring.models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "preparation_program")
public class PreparationProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private int maxGroupCapacity;

    @ManyToMany
    @JoinTable(name = "prep_to_subject",
            joinColumns = @JoinColumn(name = "preparation_program_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    public Set<Subject> subjectSet;

    @OneToMany(mappedBy = "preparationProgram", fetch = FetchType.EAGER)
    private Set<Group> groups;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "preparationProgram", fetch = FetchType.EAGER)
    public List<Candidate_info> candidateInfos;


    //______________________________BOILERPLATE LINE__________________________________


    public List<Candidate_info> getCandidateInfos() {
        return candidateInfos;
    }

    public void setCandidateInfos(List<Candidate_info> candidateInfos) {
        this.candidateInfos = candidateInfos;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Subject> getSubjectSet() {
        return subjectSet;
    }

    public void setSubjectSet(Set<Subject> subjectSet) {
        this.subjectSet = subjectSet;
    }

    public int getMaxGroupCapacity() {
        return maxGroupCapacity;
    }

    public void setMaxGroupCapacity(int maxGroupCapacity) {
        this.maxGroupCapacity = maxGroupCapacity;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
