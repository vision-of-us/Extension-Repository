package com.telerik.extension_repository.controllers;


import com.telerik.extension_repository.models.bindingModels.user.*;
import com.telerik.extension_repository.services.interfaces.AuthorityService;
import com.telerik.extension_repository.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService roleService;

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegisterUserModel registerUserModel,Model model){
        model.addAttribute("view","user/register-user");
        return "base-layout";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute EditUserModel registerUserModel, BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("view","user/register-user");
            return "base-layout";
        }

        this.userService.register(registerUserModel);

        return "redirect:/login";

    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false)String error, Model model){
        if (error != null){
            model.addAttribute("error","Wrong username or password");
        }

        model.addAttribute("view","user/login-user");
        return "base-layout";
    }

//    @PostMapping("/login")
//    public String loginUser(@Valid @ModelAttribute EditUserModel registerUserModel, BindingResult bindingResult, Model model){
//        if (bindingResult.hasErrors()){
//            model.addAttribute("view","user/login-user");
//            return "base-layout";
//        }
//
//        this.userService.register(registerUserModel);
//
//        return "redirect:/login";
//
//    }

    @ModelAttribute(name = "roles")
    private List<AuthorityModel> getRoles(){
        return this.roleService.getAll();
    }

    @GetMapping("/users/edit/{id}")
    private String getEditUserPage(Model model, @PathVariable Long id){
        EditUserModel editUserModel = this.userService.getById(id);
        model.addAttribute("user", editUserModel);
        model.addAttribute("view","edit-user");
        return "base-layout";
    }

    @PostMapping("/users/edit/{id}")
    public String  editUser(@PathVariable Long id, @ModelAttribute EditUserModel editUserModel){
        editUserModel.setId(id);
        this.userService.edit(editUserModel);
        return "redirect:/users";
    }


  //---------------------------------------------------


//    @GetMapping("/register")
//    public String getRegisterPage(@ModelAttribute("registrationModel") RegisterUserModel registrationModel){
//        return "user/register";
//    }
//
//    @PostMapping("/register")
//    public String registerUser(@Valid @ModelAttribute("registrationModel") RegisterUserModel registrationModel, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            return "user/register";
//        }
//
//        this.userService.register(registrationModel);
//
//        return "redirect:/users/login";
//    }
//
//    @GetMapping("/login")
//    public String getLoginPage(@ModelAttribute("loginUser") LoginUser loginUser){
//        return "user/login";
//    }
//
//
//    @PostMapping("/login")
//    public String loginUser(@Valid @ModelAttribute("user") LoginUser loginUser, RedirectAttributes redirectAttributes, HttpSession httpSession) {
//        LoggedUser loggedUser = this.userService.getByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
//        if (loggedUser == null){
//            List<String> errors = new ArrayList<>();
//            errors.add("Wrong username or password");
//            redirectAttributes.addFlashAttribute("errors", errors);
//            return "redirect:/users/login";
//        }
//
//        httpSession.setAttribute("user", loggedUser);
//        return "redirect:/user";
//    }
//
//    @GetMapping("/users")
//    public String getAllUsesPage(Model model){
//        List<UserModelView> users = this.userService.getAll();
//        model.addAttribute("users", users);
//        model.addAttribute("view","all-users");
//        return "base-layout";
//    }
//
//    @GetMapping("/user")
//    public String getUserPage(@ModelAttribute("loginUser") LoginUser loginUser, Model model){
//        model.addAttribute("user", loginUser);
//        model.addAttribute("view","extensions/extensions-table");
//        return "base-layout";
//    }
//
//    @GetMapping("logout")
//    public String logout(HttpSession httpSession){
//        httpSession.invalidate();
//        return "redirect:/";
//    }

}