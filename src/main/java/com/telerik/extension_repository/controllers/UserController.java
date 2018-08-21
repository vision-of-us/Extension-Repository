package com.telerik.extension_repository.controllers;


import com.telerik.extension_repository.errors.Errors;
import com.telerik.extension_repository.models.bindingModels.LoggedUser;
import com.telerik.extension_repository.models.bindingModels.LoginUser;
import com.telerik.extension_repository.models.bindingModels.RegisterUserModel;
import com.telerik.extension_repository.models.viewModels.UserModelView;
import com.telerik.extension_repository.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

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
    public String getRegisterPage(@ModelAttribute("loginUser") LoginUser loginUser){
        return "login";
    }

//    @GetMapping("/login")
//    public String getLoginPage(@RequestParam(required = false) String error, Model model){
//        if(error != null){
//            model.addAttribute("error", Errors.INVALID_CREDENTIALS);
//        }
//
//        return "login";
//    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("loginUser") LoginUser loginUser, RedirectAttributes redirectAttributes, HttpSession httpSession) {
        LoggedUser loggedUser = this.userService.getByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
        if (loggedUser == null){
            List<String> errors = new ArrayList<>();
            errors.add("Wrong username or password");
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:/users/login";
        }

        httpSession.setAttribute("loginUser", loggedUser);
        return "redirect:/";
    }



    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("registrationModel") RegisterUserModel registrationModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }

        this.userService.register(registrationModel);

        return "redirect:/users/login";
    }

    @GetMapping("/users")
    public String getAllUsesPage(Model model){
        List<UserModelView> users = this.userService.getAll();
        model.addAttribute("users", users);
        model.addAttribute("view","all-users");
        return "base-layout";
    }

    @GetMapping("/user")
    public String getUserPage(Principal principal){
        System.out.println(principal.getName());
        //this.userService.delete();
        return "user";
    }

    @GetMapping("/admin")
    public String getAdminPage(){

        return "admin";
    }


}