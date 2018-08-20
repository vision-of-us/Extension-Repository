package com.telerik.extension_repository.services;

import com.telerik.extension_repository.models.viewModels.ExtensionsModelView;

import java.util.List;
import java.util.Set;

public interface ExtensionService {

    List<ExtensionsModelView> getAll();
    List<ExtensionsModelView> getAllByName(String name);
}
