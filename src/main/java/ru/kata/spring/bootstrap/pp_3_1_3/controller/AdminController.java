package ru.kata.spring.bootstrap.pp_3_1_3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.bootstrap.pp_3_1_3.entity.Role;
import ru.kata.spring.bootstrap.pp_3_1_3.entity.User;
import ru.kata.spring.bootstrap.pp_3_1_3.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String welcomePage() {
        return "/index";
    }


    @GetMapping("/admin")
    @PostMapping("/admin/new")
    public String getAllUser(Model model, Principal principal, @ModelAttribute("newUser") User user) {
        User userActive = userService.loadUserByUsername(principal.getName());
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("userList", userService.getAll());
        model.addAttribute("userRoles", userActive.getRoles());
        model.addAttribute("userActive", userActive);
        model.addAttribute("listRoles", listRoles);
        return "admin";

    }


    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user";
    }


    @PostMapping("/admin/new")
    public String createUser(@ModelAttribute("newUser") User user) {
        if (userService.loadUserByUsername(user.getUsername()) == null) {
            userService.save(user);
        }
        return "redirect:/admin";
    }


    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        User checkUser = userService.loadUserByUsername(user.getUsername());
        if (checkUser.getId() == user.getId()) {
            userService.save(user);
        }
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.removeById(id);
        return "redirect:/admin";
    }

}
