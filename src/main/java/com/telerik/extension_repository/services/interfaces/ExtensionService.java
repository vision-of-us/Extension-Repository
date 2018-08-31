package com.telerik.extension_repository.services.interfaces;

import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.entities.enums.Status;
import com.telerik.extension_repository.models.bindingModels.extensions.AddExtensionModel;
import com.telerik.extension_repository.models.bindingModels.extensions.EditExtensionModel;
import com.telerik.extension_repository.models.bindingModels.extensions.RelatedExtensionModel;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionModelView;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionDetailsView;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionStatusView;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public interface ExtensionService {

    void persist(ExtensionDetailsView addExtensionModel);
    EditExtensionModel getByIdToEdit(Long id);
    ExtensionStatusView getById(Long id);
    void update(ExtensionStatusView extensionModel);
    void approve(ExtensionStatusView addExtensionModel);
    List<ExtensionModelView> getAll();
    List<Extension> getAllExtensions();
    ExtensionDetailsView getByIdToDetailsPage(Long id);
    List<ExtensionModelView> getAllByName(String name);
    List<ExtensionDetailsView> getAllJsons();
    void delete(Long id);
    List<ExtensionDetailsView> getAllPending();
    List<ExtensionDetailsView> getAllFeatured();
    List<ExtensionDetailsView> getAllSortedByDate();
    List<ExtensionDetailsView> getAllExt();
//    Blob downloadFile(Long id) throws IOException, SQLException;
//    Extension downloadFileAsExtension(Long id);

}
