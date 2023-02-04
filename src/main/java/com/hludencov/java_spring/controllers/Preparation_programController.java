package com.hludencov.java_spring.controllers;


import com.hludencov.java_spring.models.PreparationProgram;
import com.hludencov.java_spring.repo.DepartmentRepository;
import com.hludencov.java_spring.repo.PreparationProgramRepository;
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
@RequestMapping("/preparation_program")
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
public class Preparation_programController {
    
    @Autowired
    private PreparationProgramRepository preparationProgramRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    @GetMapping
    public String prepprogList(PreparationProgram preparationProgram, Model model){
        model.addAttribute("preparationProgram", preparationProgramRepository.findAll());
        return "preparation_program/preparation_program-main";
    }


    @GetMapping("/add")
    public String prepprogAdd(PreparationProgram preparationProgram, Model model) {
        model.addAttribute("subjectSet", subjectRepository.findAll());
        model.addAttribute("departmentSet", departmentRepository.findAll());
        return "preparation_program/preparation_program-add";
    }

    @PostMapping("/add")
    public String prepprogPostAdd(@ModelAttribute("preparationProgram") @Valid PreparationProgram preparationProgram, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("subjectSet", subjectRepository.findAll());
            model.addAttribute("departmentSet", departmentRepository.findAll());
            return "preparation_program/preparation_program-add";
        }
        preparationProgramRepository.save(preparationProgram);
        return "redirect:/preparation_program";
    }


    @GetMapping("/edit/{preparationProgram}")
    public String prepprogEdit(
            PreparationProgram preparationProgram,
            Model model) {
        model.addAttribute("subjectSet", subjectRepository.findAll());
        model.addAttribute("departmentSet", departmentRepository.findAll());
        model.addAttribute("preparationProgram", preparationProgram);
        return "preparation_program/preparation_program-edit";
    }

    @PostMapping("/edit/{preparationProgram}")
    public String prepprogPostEdit(@ModelAttribute("preparationProgram") @Valid PreparationProgram preparationProgram, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("subjectSet", subjectRepository.findAll());
            model.addAttribute("departmentSet", departmentRepository.findAll());
        model.addAttribute("preparationProgram", preparationProgram);
            return "preparation_program/preparation_program-edit";
        }
        preparationProgramRepository.save(preparationProgram);
        return "redirect:../";
    }

    @GetMapping("/show/{preparationProgram}")
    public String prepprogShow(
            PreparationProgram preparationProgram,
            Model model) {
        model.addAttribute("preparationProgram", preparationProgram);
        model.addAttribute("departmentSet", departmentRepository.findAll());
        return "preparation_program/preparation_program-show";
    }

    @GetMapping("/del/{preparationProgram}")
    public String prepprogDel(
            PreparationProgram preparationProgram) {
        preparationProgramRepository.delete(preparationProgram);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String prepprogFilter(Model model) {
        return "preparation_program/preparation_program-filter";
    }

    @PostMapping("/filter/result")
    public String prepprogResult(@RequestParam String title, Model model) {
        List<PreparationProgram> result = preparationProgramRepository.findByNameContains(title);
        model.addAttribute("result", result);
        return "preparation_program/preparation_program-filter";
    }

    @PostMapping("/filter_strict/result")
    public String prepprogStrictResult(@RequestParam String title, Model model) {
        List<PreparationProgram> result = preparationProgramRepository.findByName(title);
        model.addAttribute("result", result);
        return "preparation_program/preparation_program-filter";
    }
}
