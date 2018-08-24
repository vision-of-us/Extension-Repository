package com.telerik.extension_repository.services;

import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.entities.enums.Status;
import com.telerik.extension_repository.models.bindingModels.extensions.AddExtensionModel;
import com.telerik.extension_repository.models.bindingModels.extensions.EditExtensionModel;
import com.telerik.extension_repository.models.bindingModels.extensions.RelatedExtensionModel;
import com.telerik.extension_repository.models.bindingModels.user.AuthorityModel;
import com.telerik.extension_repository.models.bindingModels.user.EditUserModel;
import com.telerik.extension_repository.models.viewModels.extensions.ExtensionStatusView;
import com.telerik.extension_repository.models.viewModels.users.UserModelView;
import com.telerik.extension_repository.models.viewModels.users.UserView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private UserService userService;

    private ModelMapper modelMapper;

    private ExtensionService extensionService;

    @Autowired
    public AdminServiceImpl(UserService userService, ModelMapper modelMapper, ExtensionService extensionService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.extensionService = extensionService;
    }

    @Override
    public void approveExtension(AddExtensionModel addExtensionModel) {
      extensionService.approve(addExtensionModel);
    }

    @Override
    public void deleteExtension(RelatedExtensionModel relatedExtensionModel) {

    }

    @Override
    public void enableUser(UserView userView) {

    }

    @Override
    public void disableUser(UserView userView) {

    }

    @Override
    public void editExtension(ExtensionStatusView extensionStatusView) {

    }

    @Override
    public List<UserModelView> getAll() {
        return null;
    }

    @Override
    public EditUserModel getById(Long id) {
        return null;
    }
}
