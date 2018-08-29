package com.telerik.extension_repository.services;


import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.entities.GitHubData;
import com.telerik.extension_repository.entities.enums.Status;
import com.telerik.extension_repository.models.bindingModels.extensions.AddExtensionModel;
import com.telerik.extension_repository.models.bindingModels.extensions.EditExtensionModel;
import com.telerik.extension_repository.models.viewModels.extensions.*;
import com.telerik.extension_repository.repositories.ExtensionRepository;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import com.telerik.extension_repository.services.interfaces.GithubApiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExtensionServiceImpl implements ExtensionService {
    private final ExtensionRepository extensionRepository;
    private final ModelMapper modelMapper;
    private final GithubApiService githubApiService;

    @Autowired
    public ExtensionServiceImpl(ExtensionRepository extensionRepository, ModelMapper modelMapper, GithubApiService githubApiService) {
        this.extensionRepository = extensionRepository;
        this.modelMapper = modelMapper;
        this.githubApiService = githubApiService;
    }

    //    public void persist(ExtensionModel extensionModel) {
//        ModelMapper modelMapper = new ModelMapper();
//        Extension extension = modelMapper.map(extensionModel, Extension.class);
//        this.extensionRepository.saveAndFlush(extension);
//
//    }
//
@Override
public List<ExtensionDetailsView> getAllPending() {
    List<Extension> extensions = this.extensionRepository.findAllByStatus(Status.PENDING);
    List<ExtensionDetailsView> extensionModelViews = new ArrayList<>();
    for (Extension extension : extensions) {
        ExtensionDetailsView extensionModelView = this.modelMapper.map(extension, ExtensionDetailsView.class);
        extensionModelViews.add(extensionModelView);
    }
    return extensionModelViews;
}

    @Override
    public List<ExtensionDetailsView> getAllFeatured() {
        List<Extension> extensions = this.extensionRepository.findAllFeatured(true);
        List<ExtensionDetailsView> extensionModelViews = new ArrayList<>();
        for (Extension extension : extensions) {
            ExtensionDetailsView extensionModelView = this.modelMapper.map(extension, ExtensionDetailsView.class);
            extensionModelViews.add(extensionModelView);
        }
        return extensionModelViews;
    }


    @Override
    public List<ExtensionModelView> getAll() {
        List<Extension> extensions = this.extensionRepository.findAll();
        List<ExtensionModelView> extensionModelViews = new ArrayList<>();
        for (Extension extension : extensions) {
            ExtensionModelView extensionModelView = this.modelMapper.map(extension, ExtensionModelView.class);
            extensionModelViews.add(extensionModelView);
        }
        return extensionModelViews;
    }

    @Override
    public ExtensionDetailsView getByIdToDetailsPage(Long id) {
        Extension extension = this.extensionRepository.getOne(id);
        ModelMapper modelMapper = new ModelMapper();
        ExtensionDetailsView extensionModel = null;
        if (extension != null) {
            extensionModel = modelMapper.map(extension, ExtensionDetailsView.class);

        }
        return extensionModel;
    }


//    @Override
//    public Set<ExtensionModelView> getAllByName(String name) {
//        List<Extension> extensions_old = this.extensionRepository.getAllByNameOrderByNameAsc(name);
//        Set<ExtensionModelView> extensionsModelViews = new HashSet<>();
//        for (Extension extension : extensions_old) {
//            ExtensionModelView extensionsModelView = this.modelMapper.map(extension, ExtensionModelView.class);
//            extensionsModelViews.add(extensionsModelView);
//        }
//        return extensionsModelViews;
//    }

    @Override
    public List<ExtensionModelView> getAllByName(String name) {
        List<Extension> extensions = new ArrayList<>();
        if (name != null) {
            extensions = this.extensionRepository.getAllByNameOrderByNameAsc(name);
        } else {
            extensions = this.extensionRepository.findAll();
        }

        List<ExtensionModelView> extensionModelViews = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Extension extension : extensions) {
            ExtensionModelView extensionModelView = modelMapper.map(extension, ExtensionModelView.class);
            extensionModelViews.add(extensionModelView);
        }
        return extensionModelViews;
    }

    @Override
    public List<ExtensionDetailsView> getAllJsons() {
        List<Extension> extensions = this.extensionRepository.findAll();
        List<ExtensionDetailsView> extensionModelViews = new ArrayList<>();
        for (Extension extension : extensions) {
            ExtensionDetailsView extensionModelView = this.modelMapper.map(extension, ExtensionDetailsView.class);
            extensionModelViews.add(extensionModelView);
        }
        return extensionModelViews;
    }


    @Override
    public void persist(ExtensionDetailsView addExtensionModel) {
        addExtensionModel.setStatus(Status.PENDING);
        ModelMapper modelMapper = new ModelMapper();
        Extension extension = modelMapper.map(addExtensionModel, Extension.class);
        String fullUrl = extension.getSource_repository_link();

        GitHubData gitHubData = null;
        try {
            gitHubData = githubApiService.updateGithubData(fullUrl);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        extension.setGitHubData(gitHubData);
        this.extensionRepository.saveAndFlush(extension);


    }

    @Override
    public void approve(ExtensionStatusView addExtensionModel) {
        addExtensionModel.setStatus(Status.APPROVED);
        ModelMapper modelMapper = new ModelMapper();
        Extension extension = modelMapper.map(addExtensionModel, Extension.class);
        this.extensionRepository.saveAndFlush(extension);
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
    public ExtensionStatusView getById(Long id) {
        Optional<Extension> extension = this.extensionRepository.findById(id);
        ExtensionStatusView extensionStatusView = modelMapper.map(extension, ExtensionStatusView.class);
        return extensionStatusView;
    }

//    @Override
//    public void update(EditExtensionModel extensionModel) {
//        ModelMapper modelMapper = new ModelMapper();
//        Extension extension = modelMapper.map(extensionModel, Extension.class);
//        this.extensionRepository.saveAndFlush(extension);
//    }

    @Override
    public void update(ExtensionStatusView extensionModel) {
        this.extensionRepository.update(extensionModel.getName(), extensionModel.getDescription(),extensionModel.getStatus() ,extensionModel.getId());
    }

    @Override
    public void delete(Long id) {
        this.extensionRepository.deleteById(id);
    }

}
