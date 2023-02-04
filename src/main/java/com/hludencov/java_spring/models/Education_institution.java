package com.hludencov.java_spring.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "education_institution")
public class Education_institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    public String name, address;

    @OneToMany(mappedBy = "education_institution", fetch = FetchType.EAGER)
    private Set<Candidate_info> candidateInfos;

    //______________________________BOILERPLATE LINE__________________________________


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Candidate_info> getCandidateInfos() {
        return candidateInfos;
    }

    public void setCandidateInfos(Set<Candidate_info> candidateInfos) {
        this.candidateInfos = candidateInfos;
    }
}
