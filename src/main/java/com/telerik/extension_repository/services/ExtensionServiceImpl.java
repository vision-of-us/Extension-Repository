package com.telerik.extension_repository.services;


import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.models.bindingModels.ExtensionModel;
import com.telerik.extension_repository.models.bindingModels.extensions.AddExtensionModel;
import com.telerik.extension_repository.models.bindingModels.extensions.EditExtensionModel;
import com.telerik.extension_repository.models.bindingModels.extensions.RelatedExtensionModel;
import com.telerik.extension_repository.models.viewModels.extensions.*;
import com.telerik.extension_repository.repositories.ExtensionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExtensionServiceImpl implements ExtensionService {
    private final ExtensionRepository extensionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ExtensionServiceImpl(ExtensionRepository extensionRepository, ModelMapper modelMapper) {
        this.extensionRepository = extensionRepository;
        this.modelMapper = modelMapper;
    }

//    public void persist(ExtensionModel extensionModel) {
//        ModelMapper modelMapper = new ModelMapper();
//        Extension extension = modelMapper.map(extensionModel, Extension.class);
//        this.extensionRepository.saveAndFlush(extension);
//
//    }
//
//    @Override
//    public List<ExtensionsModelView> getAll() {
//        List<Extension> extensions = this.extensionRepository.findAll();
//        List<ExtensionsModelView> extensionsModelViews = new ArrayList<>();
//        for (Extension extension : extensions) {
//            ExtensionsModelView extensionsModelView = this.modelMapper.map(extension, ExtensionsModelView.class);
//            extensionsModelViews.add(extensionsModelView);
//        }
//        return extensionsModelViews;
//    }
//
//
////    @Override
////    public Set<ExtensionsModelView> getAllByName(String name) {
////        List<Extension> extensions_old = this.extensionRepository.getAllByNameOrderByNameAsc(name);
////        Set<ExtensionsModelView> extensionsModelViews = new HashSet<>();
////        for (Extension extension : extensions_old) {
////            ExtensionsModelView extensionsModelView = this.modelMapper.map(extension, ExtensionsModelView.class);
////            extensionsModelViews.add(extensionsModelView);
////        }
////        return extensionsModelViews;
////    }
//
//    @Override
//    public List<ExtensionsModelView> getAllByName(String name) {
//        List<Extension> extensions = new ArrayList<>();
//        if (name != null) {
//            extensions = this.extensionRepository.getAllByNameOrderByNameAsc(name);
//        } else {
//            extensions = this.extensionRepository.findAll();
//        }
//
//        List<ExtensionsModelView> extensionsModelViews = new ArrayList<>();
//        ModelMapper modelMapper = new ModelMapper();
//        for (Extension extension : extensions) {
//            ExtensionsModelView extensionsModelView = modelMapper.map(extension, ExtensionsModelView.class);
//            extensionsModelViews.add(extensionsModelView);
//        }
//        return extensionsModelViews;
//    }


    // ---------------------------------- From CustomerServiceImpl


    @Override
    public List<ExtensionsModelView> getAllByName(String name) {
        return null;
    }

    @Override
    public void persist(AddExtensionModel addExtensionModel) {

    }

    @Override
    public List<ExtensionView> getAllOrderByBirthDate(String type) {
        return null;
    }

    @Override
    public ExtensionDetailsView getById(Long id) {
        return null;
    }

    @Override
    public EditExtensionModel getByIdToEdit(Long id) {
        Extension extension = this.extensionRepository.getOne(id);
        ModelMapper modelMapper = new ModelMapper();
        EditExtensionModel extensionModel = null;
        if (extension != null) {
            extensionModel = modelMapper.map(extension, EditExtensionModel.class);

        }
        return extensionModel;


    }

    @Override
    public void update(EditExtensionModel extensionModel) {
        ModelMapper modelMapper = new ModelMapper();
        Extension extension = modelMapper.map(extensionModel, Extension.class);
        this.extensionRepository.saveAndFlush(extension);
    }

    @Override
    public List<ExtensionNameView> getAll() {
        return null;
    }

    @Override
    public RelatedExtensionModel getByName(String name) {
        return null;
    }

    @Override
    public ExtensionDriverView getDriverById(Long id) {
        return null;
    }
}
