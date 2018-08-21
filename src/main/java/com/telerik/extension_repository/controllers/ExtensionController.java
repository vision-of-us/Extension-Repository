package com.telerik.extension_repository.controllers;

import com.telerik.extension_repository.models.bindingModels.extensions.AddExtensionModel;
import com.telerik.extension_repository.models.bindingModels.extensions.EditExtensionModel;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionDetailsView;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionView;
import com.telerik.extension_repository.services.ExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/extensions")
public class ExtensionController {

    @Autowired
    private ExtensionService extensionService;

    @GetMapping("add")
    public String getAddExtensionPage(Model model){
        AddExtensionModel addExtensionModel = new AddExtensionModel();
        model.addAttribute("extension", addExtensionModel);
        model.addAttribute("view","/extensions/extension-modifiable");
        model.addAttribute("type","Add");
        return "base-layout";
    }

    @PostMapping("add")
    public String addExtension(@ModelAttribute AddExtensionModel addExtensionModel){
       this.extensionService.persist(addExtensionModel);
        return "redirect:/extensions/all";
    }

    @GetMapping("all")
    public String getHomePage(Model model, @RequestParam(value = "filter",required = false) String filter){
        List<ExtensionView> extensionViews = this.extensionService.getAllOrderByBirthDate(filter);
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view","/extensions/extensions-table");
        return "base-layout";
    }

    @GetMapping("{id}")
    public String getExtensionDetailsPage(Model model, @PathVariable Long id){
        ExtensionDetailsView extensionDetailsView = this.extensionService.getById(id);
        model.addAttribute("extension", extensionDetailsView);
        model.addAttribute("view","/extensions/extension-details");
        return "base-layout";
    }

    @GetMapping("edit/{id}")
    public String getEditExtensionPage(Model model, @PathVariable Long id){
        EditExtensionModel extensionModel = this.extensionService.getByIdToEdit(id);
        model.addAttribute("extension", extensionModel);
        model.addAttribute("view","/extensions/extension-modifiable");
        model.addAttribute("type","Edit");

        return "base-layout";
    }

    @PostMapping("edit/{id}")
    public String editExtension(@ModelAttribute EditExtensionModel extensionModel, @PathVariable Integer id){
        extensionModel.setId(id);
        this.extensionService.update(extensionModel);
        return "redirect:/extensions/all";
    }
}
