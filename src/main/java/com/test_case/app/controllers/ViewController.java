package com.test_case.app.controllers;

import com.test_case.app.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/login")
    String login(Model model) {
        model.addAttribute("User", new User());
        return "/login";
    }
}