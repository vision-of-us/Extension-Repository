package com.telerik.extension_repository.controllers;


import com.telerik.extension_repository.errors.Errors;
import com.telerik.extension_repository.models.bindingModels.RegisterUserModel;
import com.telerik.extension_repository.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class LoginRegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute("registrationModel") RegisterUserModel registrationModel){
        return "register";
    }

//    @GetMapping("/login")
//    public String getLoginPage() {
//        return "login";
//    }

//    @GetMapping("/register")
//    public String getRegisterPage() {
//        return "register";
//    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model){
        if(error != null){
            model.addAttribute("error", Errors.INVALID_CREDENTIALS);
        }

        return "login";
    }



    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("registrationModel") RegisterUserModel registrationModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }

        this.userService.register(registrationModel);

        return "redirect:/";
    }
}