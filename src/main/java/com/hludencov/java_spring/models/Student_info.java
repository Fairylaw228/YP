package com.hludencov.java_spring.models;

import javax.persistence.*;

@Entity
@Table(name = "student_info")
public class Student_info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne
    @JoinColumn(name = "prepatation_program_id")
    public PreparationProgram preparationProgram;

}
