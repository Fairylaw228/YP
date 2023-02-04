package com.hludencov.java_spring.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
@PreAuthorize("hasAnyAuthority('STUDENT','ADMISSION','HR')")
public class StudentController {

    @GetMapping
    public String studentPage() {
        return "student/student-main";
    }

//    @GetMapping("/generate")
}
