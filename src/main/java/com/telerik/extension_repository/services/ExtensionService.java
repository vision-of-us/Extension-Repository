package com.telerik.extension_repository.services;

import com.telerik.extension_repository.models.bindingModels.extensions.AddExtensionModel;
import com.telerik.extension_repository.models.bindingModels.extensions.EditExtensionModel;
import com.telerik.extension_repository.models.bindingModels.extensions.RelatedExtensionModel;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionsModelView;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionDetailsView;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionDriverView;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionNameView;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionView;

import java.util.List;

public interface ExtensionService {

    //List<ExtensionsModelView> getAll();
    List<ExtensionsModelView> getAllByName(String name);
    //------------------------------------------------

    void persist(AddExtensionModel addExtensionModel);
    List<ExtensionView> getAllOrderByBirthDate(String type);
    ExtensionDetailsView getById(Long id);
    EditExtensionModel getByIdToEdit(Long id);
    void update(EditExtensionModel customerModel);
    List<ExtensionNameView> getAll();
    RelatedExtensionModel getByName(String name);
    ExtensionDriverView getDriverById(Long id);
}
