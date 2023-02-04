package com.hludencov.java_spring.controllers;

import com.hludencov.java_spring.models.Candidate_info;
import com.hludencov.java_spring.models.Role;
import com.hludencov.java_spring.models.User;
import com.hludencov.java_spring.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('TEACHER','STUDENT','USER','CANDIDATE')")
@RequestMapping("/candidate")
public class CandidateInfoController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PreparationProgramRepository preparationProgramRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Education_institutionRepository educationInstitutionRepository;
    User user = new User();

    @GetMapping
    public String candidateList(Model model) {
        user = getAuthUser();

        if (user.getRoles().contains(Role.CANDIDATE)) {
            model.addAttribute("candidates", user.getCandidate_info());
        } else {
            model.addAttribute("candidates", candidateRepository.findAll());
        }
        return "candidate/candidate-main";
    }

    @GetMapping("/add")
    public String candidateAdd(Candidate_info candidate, Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        model.addAttribute("preparationPrograms", preparationProgramRepository.findAll());
        model.addAttribute("education_institutions", educationInstitutionRepository.findAll());
        model.addAttribute("users", userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "candidate/candidate-add";
    }
    @PostMapping("/add")

    public String candidatePostAdd(@ModelAttribute("candidate_info") @Valid Candidate_info candidate, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("candidates", candidateRepository.findAll());
            model.addAttribute("preparationPrograms", preparationProgramRepository.findAll());
            return "candidate/candidate-add";
        }
        candidate.user = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        candidate.submissionDate = new Date(System.currentTimeMillis());
        candidate.user.getRoles().clear();
        candidate.user.getRoles().add(Role.CANDIDATE);
        candidate.user.getRoles().add(Role.USER);

        var role = candidate.user.getRoles();

        candidate.user.setRoles(role);
        candidateRepository.save(candidate);
        return "redirect:/";
    }

    @GetMapping("/edit/{candidate_info}")
    public String candidateEdit(
            Candidate_info candidate,
            Model model) {
        model.addAttribute("preparationPrograms", preparationProgramRepository.findAll());
        model.addAttribute("candidates", candidate);
        return "candidate/candidate-edit";
    }

    @PostMapping("/edit/{candidate_info}")
    public String candidatePostEdit(@ModelAttribute("candidate_info") @Valid Candidate_info candidate, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("candidates", candidateRepository.findAll());
            model.addAttribute("preparationPrograms", preparationProgramRepository.findAll());
            return "candidate/candidate-edit";
        }
        candidateRepository.save(candidate);
        return "redirect:../";
    }

    @GetMapping("/show/{candidate_info}")
    public String candidateShow(
            Candidate_info candidate,
            Model model) {
        model.addAttribute("preparationPrograms", preparationProgramRepository.findAll());
        model.addAttribute("candidates", candidate);
        return "candidate/candidate-show";
    }

    @PreAuthorize("hasAnyAuthority('TEACHER')")
    @GetMapping("/del/{candidate_info}")
    public String candidateDel(
            Candidate_info candidate) {
        candidateRepository.delete(candidate);
        return "redirect:../";
    }

    @PreAuthorize("hasAnyAuthority('TEACHER')")
    @GetMapping("/filter")
    public String candidateFilter(Model model) {
        return "candidate/candidate-filter";
    }

    @PreAuthorize("hasAnyAuthority('TEACHER')")
    @PostMapping("/filter/result")
    public String candidateResult(@RequestParam String title, Model model) {
        List<Candidate_info> result = candidateRepository.findBySubmissionDateContains(title);
        model.addAttribute("result", result);
        return "candidate/candidate-filter";
    }

    //________________________________________UTILS______________________________________________
     private User getAuthUser() {
        return userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
