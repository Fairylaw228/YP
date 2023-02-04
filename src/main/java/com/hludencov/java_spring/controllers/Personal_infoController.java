package com.hludencov.java_spring.controllers;

import com.hludencov.java_spring.models.Personal_info;
import com.hludencov.java_spring.models.User;
import com.hludencov.java_spring.repo.Personal_infoRepository;
import com.hludencov.java_spring.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/personal_info")
public class Personal_infoController {

    @Autowired
    private Personal_infoRepository personal_infoRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public String personalInfoList(Personal_info personal_info, Model model) {
        model.addAttribute("personal_info", personal_infoRepository.findAll());
        return "personal_info/personal_info-main";
    }


    @GetMapping("/add")
    public String personalInfoAdd(@RequestParam("user") @Valid User user,Personal_info personal_info,Model model) {
        personal_info.setUser(user);
        return "personal_info/personal_info-add";
    }

    @PostMapping("/add")
    public String personalInfoPostAdd(@ModelAttribute("personal_info") @Valid Personal_info personal_info, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "personal_info/personal_info-add";
        }
        personal_infoRepository.save(personal_info);
        return "redirect:/login";
    }


    @GetMapping("/edit/{personal_info}")
    public String personalInfoEdit(
            Personal_info personal_info,
            Model model) {
        model.addAttribute("personal_info", personal_info);
        return "personal_info/personal_info-edit";
    }

    @PostMapping("/edit/{personal_info}")
    public String personalInfoPostEdit(@ModelAttribute("personal_info") @Valid Personal_info personal_info, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "personal_info/personal_info-edit";
        }
        personal_infoRepository.save(personal_info);
        return "redirect:../";
    }

    @GetMapping("/show/{personal_info}")
    public String personalInfoShow(
            Personal_info personal_info,
            Model model) {
        model.addAttribute("personal_info", personal_info);
        return "personal_info/personal_info-show";
    }

    @GetMapping("/del/{personal_info}")
    public String personalInfoDel(
            Personal_info personal_info) {
        personal_infoRepository.delete(personal_info);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String personalInfoFilter(Model model) {
        return "personal_info/personal_info-filter";
    }

    @PostMapping("/filter/result")
    public String personalInfoResult(@RequestParam String title, Model model) {
        List<Personal_info> result = personal_infoRepository.findByNameContains(title);
        model.addAttribute("result", result);
        return "personal_info/personal_info-filter";
    }

    @PostMapping("/filter_strict/result")
    public String personalInfoStrictResult(@RequestParam String title, Model model) {
        List<Personal_info> result = personal_infoRepository.findByName(title);
        model.addAttribute("result", result);
        return "personal_info/personal_info-filter";
    }
}
