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

    @PreAuthorize("hasRole('ADMIN')")
    void approveExtension(ExtensionStatusView extensionModel);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteExtension(Long id);

    void disableUser(Long id);

//    void enableUser(UserView userView);
//
//    void disableUser(UserModelView userView);

    void editExtension(ExtensionStatusView extensionStatusView);

    void deleteUserById(Long id);
}
