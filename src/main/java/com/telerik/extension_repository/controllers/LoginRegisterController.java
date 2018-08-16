package com.telerik.extension_repository.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login_register")
public class LoginRegisterController {

    @GetMapping()
    public String createHomePage(Model model) {
        return "login_and_register_tabbed_form";
    }

}