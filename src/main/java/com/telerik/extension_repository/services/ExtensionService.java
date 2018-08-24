package com.telerik.extension_repository.services;

import com.telerik.extension_repository.entities.enums.Status;
import com.telerik.extension_repository.models.bindingModels.extensions.AddExtensionModel;
import com.telerik.extension_repository.models.bindingModels.extensions.EditExtensionModel;
import com.telerik.extension_repository.models.bindingModels.extensions.RelatedExtensionModel;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionModelView;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionDetailsView;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionStatusView;

import java.util.List;

public interface ExtensionService {

    void persist(AddExtensionModel addExtensionModel);
    EditExtensionModel getByIdToEdit(Long id);
    ExtensionStatusView getById(Long id);
    void update(ExtensionStatusView extensionModel);
    void approve(AddExtensionModel addExtensionModel);
    List<ExtensionModelView> getAll();
    ExtensionDetailsView getByIdToDetailsPage(Long id);
    List<ExtensionModelView> getAllByName(String name);
    void delete(ExtensionStatusView id);
    List<ExtensionModelView> getAllPending();

}
