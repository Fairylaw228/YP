package com.hludencov.java_spring.models;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PositiveOrZero
    private int capacity;

    @Value("empty")
    private String name;


    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "user_to_group",
            joinColumns = @JoinColumn(name = "groups_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    public Set<User> users;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    public User teacher_organizer;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "preparation_program_id")
    public PreparationProgram preparationProgram;

    public boolean isFull(){
        return users.size() >= capacity;
    }


    //______________________________BOILERPLATE LINE__________________________________

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public PreparationProgram getPreparationProgram() {
        return preparationProgram;
    }

    public void setPreparationProgram(PreparationProgram preparation_program) {
        this.preparationProgram = preparation_program;
    }

    public User getTeacher_organizer() {
        return teacher_organizer;
    }

    public void setTeacher_organizer(User teacher_organizer) {
        this.teacher_organizer = teacher_organizer;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
