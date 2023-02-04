package com.hludencov.java_spring.controllers;


import com.hludencov.java_spring.models.Subject;
import com.hludencov.java_spring.repo.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/subject")
@PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    public String subjectList(Model model){
        model.addAttribute("subject", subjectRepository.findAll());
        return "subject/subject-main";
    }

    @GetMapping("/add")
    public String subjectAdd(Subject subject) {
        return "subject/subject-add";
    }

    @PostMapping("/add")
    public String subjectPostAdd(@ModelAttribute("subject") @Valid Subject subject, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "subject/subject-add";
        }
        subjectRepository.save(subject);
        return "redirect:/subject";
    }


    @GetMapping("/edit/{subject}")
    public String subjectEdit(
            Subject subject,
            Model model) {
        model.addAttribute("subject", subject);
        return "subject/subject-edit";
    }

    @PostMapping("/edit/{subject}")
    public String subjectPostEdit(@ModelAttribute("subject") @Valid Subject subject, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "subject/subject-edit";
        }
        subjectRepository.save(subject);
        return "redirect:../";
    }

    @GetMapping("/show/{subject}")
    public String subjectShow(
            Subject subject,
            Model model) {
        model.addAttribute("subject", subject);
        return "subject/subject-show";
    }

    @GetMapping("/del/{subject}")
    public String subjectDel(
            Subject subject) {
        subjectRepository.delete(subject);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String subjectFilter(Model model) {
        return "subject/subject-filter";
    }

    @PostMapping("/filter/result")
    public String subjectResult(@RequestParam String title, Model model) {
        List<Subject> result = subjectRepository.findByNameContains(title);
        model.addAttribute("result", result);
        return "subject/subject-filter";
    }

    @PostMapping("/filter_strict/result")
    public String subjectStrictResult(@RequestParam String title, Model model) {
        List<Subject> result = subjectRepository.findByName(title);
        model.addAttribute("result", result);
        return "subject/subject-filter";
    }
}
