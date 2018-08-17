package com.telerik.extension_repository.services;

import com.telerik.extension_repository.models.bindingModels.EditUserModel;
import com.telerik.extension_repository.models.bindingModels.RegisterUserModel;
import com.telerik.extension_repository.models.viewModels.UserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void persist(RegisterUserModel userModel);
    List<UserModel> getAll();
    EditUserModel getById(Long id);
    void  edit(EditUserModel editUserModel);
}
