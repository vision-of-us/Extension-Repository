package com.telerik.extension_repository.controllers;

import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionDetailsView;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest_api/extensions")
public class RestHomeController {

    private ExtensionService extensionService;

    @Autowired
    public RestHomeController(ExtensionService extensionService) {
        this.extensionService = extensionService;
    }

    @GetMapping("/")
    public List<ExtensionDetailsView> getAllExtensions(){
        return this.extensionService.getAllJsons();
    }

    @GetMapping("/pending/")
    List<ExtensionDetailsView> findByName() {
        return extensionService.getAllPending();
    }


}