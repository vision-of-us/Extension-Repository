package com.telerik.extension_repository.controllers;

import com.telerik.extension_repository.exceptions.StorageFileNotFoundException;
import com.telerik.extension_repository.models.bindingModels.extensions.AddExtensionModel;
import com.telerik.extension_repository.models.bindingModels.extensions.EditExtensionModel;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionDetailsView;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionModelView;
import com.telerik.extension_repository.services.ExtensionService;
import com.telerik.extension_repository.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/extensions")
public class ExtensionController {

    @Autowired
    private ExtensionService extensionService;

    @Autowired
    private StorageService storageService;

    @GetMapping("add")
    public String getAddExtensionPage(Model model){
        AddExtensionModel addExtensionModel = new AddExtensionModel();
        model.addAttribute("extension", addExtensionModel);
        model.addAttribute("view","/extensions/extension-add");
        model.addAttribute("type","Add");
        return "base-layout";
    }

//    // WO
//    @PostMapping("add")
//    public String addExtension(@ModelAttribute AddExtensionModel addExtensionModel){
//       this.extensionService.persist(addExtensionModel);
//        return "redirect:/extensions/all";
//    }

    // WO
    @PostMapping("add")
    public String addExtension(@ModelAttribute AddExtensionModel addExtensionModel){
        this.extensionService.persist(addExtensionModel);
        return "redirect:/extensions/all";
    }

    @GetMapping("add/files")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(ExtensionController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

//    @GetMapping("all")
//    public String getAllExtensionsPage(Model model, @RequestParam(value = "name", required = false) String name){
//        List<ExtensionModelView> extensionViews = this.extensionService.getAllByName(name);
//        model.addAttribute("extensions", extensionViews);
//        model.addAttribute("view","/extensions/extensions-table");
//        return "base-layout";
//    }

    @GetMapping("all")
    public String getAllExtensionPage(Model model){
        List<ExtensionModelView> extensionViews = this.extensionService.getAll();
        model.addAttribute("extensions", extensionViews);
        model.addAttribute("view","/extensions/extensions-table");
        return "base-layout";
    }

    @GetMapping("{id}")
    public String getExtensionDetailsPage(Model model, @PathVariable Long id){
        ExtensionDetailsView extensionDetailsView = this.extensionService.getByIdToDetailsPage(id);
        model.addAttribute("extension", extensionDetailsView);
        model.addAttribute("view","/extensions/extension-details");
        return "base-layout";
    }

    @GetMapping("edit/{id}")
    public String getEditExtensionPage(Model model, @PathVariable Long id){
        EditExtensionModel extensionModel = this.extensionService.getByIdToEdit(id);
        model.addAttribute("view","/extensions/extension-edit");
        model.addAttribute("type","Edit");
        model.addAttribute("extension", extensionModel);
        return "base-layout";
    }

    @PostMapping("edit/{id}")
    public String editExtension(@ModelAttribute EditExtensionModel extensionModel, @PathVariable Long id){
        extensionModel.setId(id);
        this.extensionService.update(extensionModel);
        return "redirect:/extensions/all";
    }


    @GetMapping("delete/{id}")
    public String getDeleteExtensionPage(Model model, @PathVariable Long id){
        EditExtensionModel extensionModel = this.extensionService.getByIdToEdit(id);
        model.addAttribute("view","/extensions/extension-edit");
        model.addAttribute("type","Delete");
        model.addAttribute("extension", extensionModel);
        return "base-layout";
    }

    @PostMapping("delete/{id}")
    public String deletePart(@ModelAttribute EditExtensionModel extensionModel, @PathVariable Long id){
        extensionModel.setId(id);
        this.extensionService.delete(extensionModel);
        return "redirect:/extensions/all";
    }



}
