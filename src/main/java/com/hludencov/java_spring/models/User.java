package com.hludencov.java_spring.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Document> documents;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "user_to_group",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "groups_id"))
    private Set<Group> groups;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "department_id" )
    private Department department;

    @OneToOne(mappedBy = "user",cascade = CascadeType.REMOVE)
    private Personal_info personal_info;

    @OneToOne(mappedBy = "user",cascade = CascadeType.REMOVE)
    private Candidate_info candidate_info;


    @OneToOne(mappedBy = "user",cascade = CascadeType.REMOVE)
    private Teacher_info teacher_info;


    @OneToMany(mappedBy = "teacher_organizer", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Set<Group> teacher_org_groups;


    //______________________________BOILERPLATE LINE__________________________________


    public Set<Group> getTeacher_org_groups() {
        return teacher_org_groups;
    }

    public void setTeacher_org_groups(Set<Group> teacher_org_groups) {
        this.teacher_org_groups = teacher_org_groups;
    }

    public Teacher_info getTeacher_info() {
        return teacher_info;
    }

    public void setTeacher_info(Teacher_info teacher_info) {
        this.teacher_info = teacher_info;
    }

    public Candidate_info getCandidate_info() {
        return candidate_info;
    }

    public void setCandidate_info(Candidate_info candidate_info) {
        this.candidate_info = candidate_info;
    }

    public Personal_info getPersonal_info() {
        return personal_info;
    }

    public void setPersonal_info(Personal_info personal_info) {
        this.personal_info = personal_info;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String nickname) {
        this.login = nickname;
    }

    public Long getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    public String getLogin() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
