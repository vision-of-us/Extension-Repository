package com.telerik.extension_repository.services;

import com.telerik.extension_repository.models.bindingModels.extensions.AddExtensionModel;
import com.telerik.extension_repository.models.bindingModels.extensions.EditExtensionModel;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionModelView;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionDetailsView;

import java.util.List;

public interface ExtensionService {

    void persist(AddExtensionModel addExtensionModel);
    EditExtensionModel getByIdToEdit(Long id);
    void update(EditExtensionModel extensionModel);
    List<ExtensionModelView> getAll();
    List<ExtensionModelView> getAllByName(String name);
    void delete(EditExtensionModel id);
}
