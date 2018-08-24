package com.telerik.extension_repository.controllers;


import com.telerik.extension_repository.models.bindingModels.extensions.AddExtensionModel;
import com.telerik.extension_repository.models.bindingModels.user.LoggedUser;
import com.telerik.extension_repository.models.bindingModels.user.LoginUser;
import com.telerik.extension_repository.models.bindingModels.user.RegisterUserModel;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionModelView;
import com.telerik.extension_repository.models.viewModels.users.UserModelView;
import com.telerik.extension_repository.services.AdminService;
import com.telerik.extension_repository.services.ExtensionService;
import com.telerik.extension_repository.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ExtensionService extensionService;


    @GetMapping()
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
        model.addAttribute("view","/extensions/extensions-table");
        return "base-layout";
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