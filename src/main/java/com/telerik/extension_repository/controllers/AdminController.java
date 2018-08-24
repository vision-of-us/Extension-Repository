package com.telerik.extension_repository.controllers;


import com.telerik.extension_repository.models.viewModels.extensions.ExtensionModelView;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionStatusView;
import com.telerik.extension_repository.services.AdminService;
import com.telerik.extension_repository.services.ExtensionService;
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

    @GetMapping("pending/approve/{id}")
    public String getApproveExtensionPage(Model model,@PathVariable Long id){
        ExtensionStatusView extensionStatusView = this.extensionService.getById(id);
        model.addAttribute("type","Approve");
        model.addAttribute("view","/admin/admin-extensions-modifiable");
        model.addAttribute("extension", extensionStatusView);
        return "base-layout";
    }

    @PostMapping("pending/delete/{id}")
    public String deleteExtension(@ModelAttribute ExtensionStatusView editPartModel, @PathVariable Long id){
        editPartModel.setId(id);
        this.extensionService.delete(editPartModel);
        return "redirect:/admin/pending";
    }

    @GetMapping("pending/delete/{id}")
    public String getDeletePartPage(Model model, @PathVariable Long id){
        ExtensionStatusView extensionStatusView = this.extensionService.getById(id);
        model.addAttribute("type","Delete");
        model.addAttribute("view","/admin/admin-extensions-modifiable");
        model.addAttribute("extension",extensionStatusView);
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