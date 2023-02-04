package com.hludencov.java_spring.models;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public enum Role implements GrantedAuthority {
    USER, ADMIN, CANDIDATE, TEACHER, STUDENT, HR, ADMISSION, ARCHIVE;
    @Override
    public String getAuthority() {
        return name();
    }
}
