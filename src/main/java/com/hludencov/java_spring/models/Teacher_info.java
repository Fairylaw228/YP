package com.hludencov.java_spring.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.Set;


@Entity
@Table(name = "teacher_info")
public class Teacher_info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PastOrPresent
    public Date teachSince;

    @ManyToMany
    @JoinTable(name = "teacher_to_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_info_id"))
    public Set<Subject> subjectList;


    @OneToOne(fetch = FetchType.EAGER)
    public User user;


    //______________________________BOILERPLATE LINE__________________________________


    public User getUser() {
        return user;
    }

    public void setUser(User user_teacher_info) {
        this.user = user_teacher_info;
    }

    public Set<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(Set<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTeachSince() {
        return teachSince;
    }

    public void setTeachSince(Date teachSince) {
        this.teachSince = teachSince;
    }
}
