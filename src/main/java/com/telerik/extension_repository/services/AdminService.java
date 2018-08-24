package com.telerik.extension_repository.services;

import com.telerik.extension_repository.models.bindingModels.extensions.AddExtensionModel;
import com.telerik.extension_repository.models.bindingModels.extensions.EditExtensionModel;
import com.telerik.extension_repository.models.bindingModels.extensions.RelatedExtensionModel;
import com.telerik.extension_repository.models.bindingModels.user.EditUserModel;
import com.telerik.extension_repository.models.bindingModels.user.LoggedUser;
import com.telerik.extension_repository.models.bindingModels.user.LoginUser;
import com.telerik.extension_repository.models.bindingModels.user.RegisterUserModel;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionStatusView;
import com.telerik.extension_repository.models.viewModels.users.UserModelView;
import com.telerik.extension_repository.models.viewModels.users.UserView;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AdminService{

    void approveExtension(AddExtensionModel addExtensionModel);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteExtension(RelatedExtensionModel relatedExtensionModel);

    void enableUser(UserView userView);

    void disableUser(UserView userView);

    void editExtension(ExtensionStatusView extensionStatusView);

    List<UserModelView> getAll();

    EditUserModel getById(Long id);

}
