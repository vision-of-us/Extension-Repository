package com.telerik.extension_repository.controllers;

import com.telerik.extension_repository.models.viewModels.ExtensionsModelView;
import com.telerik.extension_repository.services.ExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("extensions/")

public class ExtensionController {

    @Autowired
    private ExtensionService extensionService;

    @GetMapping("all")
    public String getExtensionsTablePage(Model model, @RequestParam(value = "name", required = false) String name) {
        List<ExtensionsModelView> extensionsModelViews = this.extensionService.getAllByName(name);
        model.addAttribute("extensions", extensionsModelViews);
        model.addAttribute("view", "/extensions/extensions-table");
        return "base-layout";
    }

}