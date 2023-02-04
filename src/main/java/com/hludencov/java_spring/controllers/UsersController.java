package com.hludencov.java_spring.controllers;

import com.hludencov.java_spring.models.Role;
import com.hludencov.java_spring.models.User;
import com.hludencov.java_spring.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'HR')")
public class UsersController {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user")
    public String userMain(Model model) {
        Iterable<User> users = usersRepository.findActive();
        model.addAttribute("users", users);
        return "user/user-main";
    }

    @GetMapping("/user/add")
    public String userAdd(User user, Model model) {
        model.addAttribute("roles", Role.values());
        return "user/user-add";
    }

    @PostMapping("/user/add")
    public String userPostAdd(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/user-add";
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        return "redirect:/user";
    }


    @GetMapping("/user/edit/{user}")
    public String userEdit(
            User user,
            Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user/user-edit";
    }

    @PostMapping("/user/edit/{user}")
    public String userPostEdit(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/user-edit";
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        return "redirect:../";
    }

    @GetMapping("/user/show/{user}")
    public String userShow(
            User user,
            Model model) {
        model.addAttribute("user", user);
        return "user/user-show";
    }

    @GetMapping("/user/del/{user}")
    public String userDel(
            User user) {
        user.setActive(false);
        usersRepository.save(user);
        return "redirect:../";
    }

    @GetMapping("/user/filter")
    public String userFilter(Model model) {
        return "user/user-filter";
    }

    @PostMapping("/user/filter/result")
    public String userResult(@RequestParam String title, Model model) {
        List<User> result = usersRepository.findByLoginContains(title);
        model.addAttribute("result", result);
        return "user/user-filter";
    }

    @PostMapping("/user/filter_strict/result")
    public String userStrictResult(@RequestParam String title, Model model) {
        List<User> result = usersRepository.findByLogin(title);
        model.addAttribute("result", result);
        return "user/user-filter";
    }
}
