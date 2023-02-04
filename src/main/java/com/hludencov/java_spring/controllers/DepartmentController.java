package com.hludencov.java_spring.controllers;


import com.hludencov.java_spring.models.Department;
import com.hludencov.java_spring.repo.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/department")
@PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping
    public String departmentList(Department department, Model model){
        model.addAttribute("department", departmentRepository.findAll());
        return "department/department-main";
    }


    @GetMapping("/add")
    public String departmentAdd(Department department) {
        return "department/department-add";
    }

    @PostMapping("/add")
    public String departmentPostAdd(@ModelAttribute("department") @Valid Department department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "department/department-add";
        }
        departmentRepository.save(department);
        return "redirect:/department";
    }


    @GetMapping("/edit/{department}")
    public String departmentEdit(
            Department department,
            Model model) {
        model.addAttribute("department", department);
        return "department/department-edit";
    }

    @PostMapping("/edit/{department}")
    public String departmentPostEdit(@ModelAttribute("department") @Valid Department department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "department/department-edit";
        }
        departmentRepository.save(department);
        return "redirect:../";
    }

    @GetMapping("/show/{department}")
    public String departmentShow(
            Department department,
            Model model) {
        model.addAttribute("department", department);
        return "department/department-show";
    }

    @GetMapping("/del/{department}")
    public String departmentDel(
            Department department) {
        departmentRepository.delete(department);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String departmentFilter(Model model) {
        return "department/department-filter";
    }

    @PostMapping("/filter/result")
    public String departmentResult(@RequestParam String title, Model model) {
        List<Department> result = departmentRepository.findByNameContains(title);
        model.addAttribute("result", result);
        return "department/department-filter";
    }

    @PostMapping("/filter_strict/result")
    public String departmentStrictResult(@RequestParam String title, Model model) {
        List<Department> result = departmentRepository.findByName(title);
        model.addAttribute("result", result);
        return "department/department-filter";
    }
}
