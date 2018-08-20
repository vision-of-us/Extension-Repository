package com.telerik.extension_repository.services;


import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.models.bindingModels.ExtensionModel;
import com.telerik.extension_repository.models.viewModels.ExtensionsModelView;
import com.telerik.extension_repository.repositories.ExtensionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExtensionServiceImpl implements ExtensionService {
    private final ExtensionRepository extensionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ExtensionServiceImpl(ExtensionRepository extensionRepository, ModelMapper modelMapper) {
        this.extensionRepository = extensionRepository;
        this.modelMapper = modelMapper;
    }

    public void persist(ExtensionModel extensionModel) {
        ModelMapper modelMapper = new ModelMapper();
        Extension extension = modelMapper.map(extensionModel, Extension.class);
        this.extensionRepository.saveAndFlush(extension);

    }

    @Override
    public List<ExtensionsModelView> getAll() {
        List<Extension> extensions = this.extensionRepository.findAll();
        List<ExtensionsModelView> extensionsModelViews = new ArrayList<>();
        for (Extension extension : extensions) {
            ExtensionsModelView extensionsModelView = this.modelMapper.map(extension, ExtensionsModelView.class);
            extensionsModelViews.add(extensionsModelView);
        }
        return extensionsModelViews;
    }


//    @Override
//    public Set<ExtensionsModelView> getAllByName(String name) {
//        List<Extension> extensions = this.extensionRepository.getAllByNameOrderByNameAsc(name);
//        Set<ExtensionsModelView> extensionsModelViews = new HashSet<>();
//        for (Extension extension : extensions) {
//            ExtensionsModelView extensionsModelView = this.modelMapper.map(extension, ExtensionsModelView.class);
//            extensionsModelViews.add(extensionsModelView);
//        }
//        return extensionsModelViews;
//    }

    @Override
    public List<ExtensionsModelView> getAllByName(String name) {
        List<Extension> extensions = new ArrayList<>();
        if (name != null) {
            extensions = this.extensionRepository.getAllByNameOrderByNameAsc(name);
        } else {
            extensions = this.extensionRepository.findAll();
        }

        List<ExtensionsModelView> extensionsModelViews = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Extension extension : extensions) {
            ExtensionsModelView extensionsModelView = modelMapper.map(extension, ExtensionsModelView.class);
            extensionsModelViews.add(extensionsModelView);
        }
        return extensionsModelViews;
    }
}
