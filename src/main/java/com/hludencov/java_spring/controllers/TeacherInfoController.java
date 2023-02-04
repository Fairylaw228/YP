package com.hludencov.java_spring.controllers;


import com.hludencov.java_spring.models.Teacher_info;
import com.hludencov.java_spring.repo.SubjectRepository;
import com.hludencov.java_spring.repo.TeachersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/teacher")
@PreAuthorize("hasAnyAuthority('TEACHER')")
public class TeacherInfoController {
    @Autowired
    private TeachersRepository teachersRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    public String teacherList(Model model) {
        model.addAttribute("teacher", teachersRepository.findAll());
        return "teacher/teacher-main";
    }

    @GetMapping("/add")
    public String teacherAdd(Teacher_info teacher, Model model) {
        model.addAttribute("teacher", teachersRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        return "teacher/teacher-add";
    }

    @PostMapping("/add")
    public String teacherPostAdd(@ModelAttribute("teacher") @Valid Teacher_info teacher, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("teacher", teachersRepository.findAll());
            model.addAttribute("subjects", subjectRepository.findAll());
            return "teacher/teacher-add";
        }
        teachersRepository.save(teacher);
        return "redirect:/teacher";
    }


    @GetMapping("/edit/{teacher}")
    public String teacherEdit(
            Teacher_info teacher,
            Model model) {
        model.addAttribute("teacher", teachersRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("teacher", teacher);
        return "teacher/teacher-edit";
    }

    @PostMapping("/edit/{teacher}")
    public String teacherPostEdit(@ModelAttribute("teacher") @Valid Teacher_info teacher, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("teacher", teachersRepository.findAll());
            model.addAttribute("subjects", subjectRepository.findAll());
            return "teacher/teacher-edit";
        }
        teachersRepository.save(teacher);
        return "redirect:../";
    }

    @GetMapping("/del/{subject}")
    public String teachertDel(
            Teacher_info teacher) {
        teachersRepository.delete(teacher);
        return "redirect:../";
    }


}
