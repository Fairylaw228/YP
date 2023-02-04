package com.hludencov.java_spring.controllers;


import com.hludencov.java_spring.models.Education_institution;
import com.hludencov.java_spring.repo.Education_institutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/education")
@PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
public class EducationInstitutionController {
     @Autowired
    private Education_institutionRepository education_institutionRepository;

    @GetMapping
    public String educationList(Education_institution education_institution,Model model){
        model.addAttribute("education_institution", education_institutionRepository.findAll());
        return "education/education-main";
    }

    @GetMapping("/add")
    public String educationAdd(Education_institution education_institution) {
        return "education/education-add";
    }

    @PostMapping("/add")
    public String educationPostAdd(@ModelAttribute("education_institution") @Valid Education_institution education_institution, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "education/education-add";
        }
        education_institutionRepository.save(education_institution);
        return "redirect:/education";
    }


    @GetMapping("/edit/{education_institution}")
    public String educationEdit(
            Education_institution education_institution,
            Model model) {
        model.addAttribute("education_institution", education_institution);
        return "education/education-edit";
    }

    @PostMapping("/edit/{education_institution}")
    public String educationPostEdit(@ModelAttribute("education_institution") @Valid Education_institution education_institution, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "education/education-edit";
        }
        education_institutionRepository.save(education_institution);
        return "redirect:../";
    }

    @GetMapping("/show/{education_institution}")
    public String educationShow(
            Education_institution education_institution,
            Model model) {
        model.addAttribute("education_institution", education_institution);
        return "education/education-show";
    }

    @GetMapping("/del/{education_institution}")
    public String educationDel(
            Education_institution education_institution) {
        education_institutionRepository.delete(education_institution);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String educationFilter(Model model) {
        return "education/education-filter";
    }

    @PostMapping("/filter/result")
    public String educationResult(@RequestParam String title, Model model) {
        List<Education_institution> result = education_institutionRepository.findByNameContains(title);
        model.addAttribute("result", result);
        return "education/education-filter";
    }

    @PostMapping("/filter_strict/result")
    public String educationStrictResult(@RequestParam String title, Model model) {
        List<Education_institution> result = education_institutionRepository.findByName(title);
        model.addAttribute("result", result);
        return "education/education-filter";
    }
}
