package com.telerik.extension_repository.controllers;


import com.telerik.extension_repository.exceptions.UserNotFoundException;
import com.telerik.extension_repository.models.bindingModels.user.EditUserModel;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionModelView;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionStatusView;
import com.telerik.extension_repository.services.interfaces.AdminService;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import com.telerik.extension_repository.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExtensionService extensionService;


    @GetMapping("extensions")
    public String getAdminPage(Model model){
        List<ExtensionModelView> extensionViews = this.extensionService.getAll();
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view","/extensions/extensions-table");
        return "base-layout";
    }

    @GetMapping("pending")
    public String getPendingExtensions(Model model){
        List<ExtensionModelView> extensionViews = this.extensionService.getAllPending();
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view","/admin/admin-pending-extensions");
        return "base-layout";
    }

    @GetMapping("pending/edit/{id}")
    public String getEditPage(Model model, @PathVariable Long id){
        ExtensionStatusView extensionStatusView = this.extensionService.getById(id);
        model.addAttribute("type","Edit");
        model.addAttribute("view","/admin/admin-extensions-modifiable");
        model.addAttribute("extension", extensionStatusView);
        return "base-layout";
    }

    @PostMapping("pending/edit/{id}")
    public String editExtension(@Valid @ModelAttribute("extensionStatusView") ExtensionStatusView extensionStatusView, @PathVariable Long id){
        extensionStatusView.setId(id);
        this.extensionService.update(extensionStatusView);
        return "redirect:/admin/pending";
    }

//    @GetMapping("pending/approve/{id}")
//    public String getApproveExtensionPage(Model model,@PathVariable Long id){
//        ExtensionStatusView extensionStatusView = this.extensionService.getById(id);
//        model.addAttribute("type","Approve");
//        model.addAttribute("view","/admin/admin-extensions-modifiable");
//        model.addAttribute("extension", extensionStatusView);
//        return "base-layout";
//    }

    @GetMapping("pending/approve/{id}")
    public String getApproveExtensionPage(Model model,@PathVariable Long id){
        ExtensionStatusView extensionStatusView = this.extensionService.getById(id);
        this.extensionService.approve(extensionStatusView);
        model.addAttribute("view","/admin/admin-pending-extensions");
        return "base-layout";
    }


    @PostMapping("pending/approve/{id}")
    public String approveExtension(@ModelAttribute ExtensionStatusView editPartModel, @PathVariable Long id){
        editPartModel.setId(id);
        this.extensionService.approve(editPartModel);
        return "redirect:/admin/pending";
    }


//    @PostMapping("pending/delete/{id}")
//    public String deleteExtension(@ModelAttribute ExtensionStatusView editPartModel, @PathVariable Long id){
//        editPartModel.setId(id);
//        this.extensionService.delete(id);
//        return "redirect:/admin/pending";
//    }

// WO
//    @GetMapping("pending/delete/{id}")
//    public String getDeletePartPage(Model model, @PathVariable Long id){
//        ExtensionStatusView extensionStatusView = this.extensionService.getById(id);
//        model.addAttribute("type","Delete");
//        model.addAttribute("view","/admin/admin-extensions-modifiable");
//        model.addAttribute("extension",extensionStatusView);
//        return "base-layout";
//    }

    @PostMapping("pending/delete/{id}")
    public String deleteExtension(@PathVariable Long id, Model model) {
            this.adminService.deleteExtension(id);
        return "redirect:/admin/pending";
    }

    @GetMapping("/users")
    public String getAllUsersPage(Model model){
        List<EditUserModel> users = this.userService.getAll();
        model.addAttribute("users", users);
        model.addAttribute("view","/admin/all-users");
        return "base-layout";
    }

    @GetMapping("/user")
    public String getUserByUsername(Model model, @RequestParam(value = "username", required = true) String username){
        EditUserModel userModel = this.userService.getUserByUsername(username);
        model.addAttribute("user", userModel);
        model.addAttribute("view","/admin/all-users");
        return "base-layout";
    }

    @PostMapping("/users/delete/{userId}")
    public String deleteUser(@PathVariable Long userId, Model model) {
        EditUserModel user = this.userService.getById(userId);
        if (user != null) {
            this.adminService.deleteUserById(userId);
        }

        return "redirect:/admin/users";
    }

    @PostMapping("/users/disableUser/{userId}")
    public String changeUserAccountAccess(@PathVariable Long userId, Model model) {
        EditUserModel user = this.userService.getById(userId);
        if (user != null) {
            this.adminService.disableUser(userId);
        }

        return "redirect:/admin/users";
    }


    @ExceptionHandler(UserNotFoundException.class)
    public String catchUserNotFoundException() {

        return "exceptions/user-not-found";
    }

//    @PostMapping("pending")
//    public String approveExtensions(@Valid @ModelAttribute("addExtensionModel") AddExtensionModel addExtensionModel, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            return "admin";
//        }
//
//        this.adminService.register(registrationModel);
//
//        return "redirect:/users/login";
//    }
//
//    @GetMapping("all")
//    public String getAllExtensionPage(Model model){
//        List<ExtensionModelView> extensionViews = this.extensionService.getAll();
//        model.addAttribute("extensions", extensionViews);
//        model.addAttribute("view","/extensions/extensions-table");
//        return "base-layout";
//    }
}