package com.telerik.extension_repository.controllers;


import com.telerik.extension_repository.models.bindingModels.user.LoggedUser;
import com.telerik.extension_repository.models.bindingModels.user.LoginUser;
import com.telerik.extension_repository.models.bindingModels.user.RegisterUserModel;
import com.telerik.extension_repository.models.viewModels.users.UserModelView;
import com.telerik.extension_repository.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute("registrationModel") RegisterUserModel registrationModel){
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("registrationModel") RegisterUserModel registrationModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "user/register";
        }

        this.userService.register(registrationModel);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String getLoginPage(@ModelAttribute("loginUser") LoginUser loginUser){
        return "user/login";
    }


    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("user") LoginUser loginUser, RedirectAttributes redirectAttributes, HttpSession httpSession) {
        LoggedUser loggedUser = this.userService.getByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
        if (loggedUser == null){
            List<String> errors = new ArrayList<>();
            errors.add("Wrong username or password");
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:/users/login";
        }

        httpSession.setAttribute("user", loggedUser);
        return "redirect:/user";
    }

    @GetMapping("/users")
    public String getAllUsesPage(Model model){
        List<UserModelView> users = this.userService.getAll();
        model.addAttribute("users", users);
        model.addAttribute("view","all-users");
        return "base-layout";
    }

    @GetMapping("/user")
    public String getUserPage(@ModelAttribute("loginUser") LoginUser loginUser, Model model){
        model.addAttribute("user", loginUser);
        model.addAttribute("view","extensions/extensions-table");
        return "base-layout";
    }

    @GetMapping("logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

}