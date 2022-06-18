package ru.kata.spring.bootstrap.pp_3_1_3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.bootstrap.pp_3_1_3.entity.User;


@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping()
    public String index(@AuthenticationPrincipal User userActive, Model model) {
        model.addAttribute("userActive", userActive);
        return "user";
    }
}
