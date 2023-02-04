package com.hludencov.java_spring.controllers;

import com.hludencov.java_spring.models.Summary;
import com.hludencov.java_spring.repo.CandidateRepository;
import com.hludencov.java_spring.repo.Education_institutionRepository;
import com.hludencov.java_spring.repo.SubjectRepository;
import com.hludencov.java_spring.repo.SummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/summary")
@PreAuthorize("hasAnyAuthority('HR', 'ADMISSION','STUDENT','TEACHER')")
public class SummaryController {
    @Autowired
    private SummaryRepository summaryRepository;

    @Autowired
    private Education_institutionRepository education_institutionRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping
    public String summaryList(Model model) {
        model.addAttribute("summary", summaryRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("candidate", candidateRepository.findAll());
        return "summary/summary-main";
    }

    @GetMapping("/add")
    public String summaryAdd(Summary summary, Model model) {
        model.addAttribute("candidate", candidateRepository.findAll());
        model.addAttribute("education_institutions", education_institutionRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        return "summary/summary-add";
    }

    @PostMapping("/add")
    public String summaryPostAdd(@ModelAttribute("summary") @Valid Summary summary, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("candidate", candidateRepository.findAll());
            model.addAttribute("education_institutions", education_institutionRepository.findAll());
            model.addAttribute("subjects", subjectRepository.findAll());
            return "summary/summary-add";
        }
        summaryRepository.save(summary);
        return "redirect:/summary";
    }


    @GetMapping("/edit/{summary}")
    public String summaryEdit(
            Summary summary,
            Model model) {
        model.addAttribute("candidate", candidateRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("summary", summary);
        return "summary/summary-edit";
    }

    @PostMapping("/edit/{summary}")
    public String summaryPostEdit(@ModelAttribute("summary") @Valid Summary summary, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("candidate", candidateRepository.findAll());
            model.addAttribute("subjects", subjectRepository.findAll());
            model.addAttribute("candidate", candidateRepository.findAll());
            return "summary/summary-edit";
        }
        summaryRepository.save(summary);
        return "redirect:../";
    }

    @GetMapping("/show/{summary}")
    public String summaryShow(
            Summary summary,
            Model model) {
        model.addAttribute("summ", summary);
        return "summary/summary-show";
    }

    @GetMapping("/del/{summary}")
    public String summaryDel(
            Summary summary) {
        summaryRepository.delete(summary);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String summaryFilter(Model model) {
        return "summary/summary-filter";
    }

    @PostMapping("/filter/result")
    public String summaryResult(@RequestParam String title, Model model) {
        List<Summary> result = summaryRepository.findByMark(Integer.parseInt(title));
        model.addAttribute("result", result);
        return "summary/summary-filter";
    }

}
