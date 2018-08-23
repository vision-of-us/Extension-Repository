package com.telerik.extension_repository.services;

import com.telerik.extension_repository.models.bindingModels.user.EditUserModel;
import com.telerik.extension_repository.models.bindingModels.user.LoggedUser;
import com.telerik.extension_repository.models.bindingModels.user.LoginUser;
import com.telerik.extension_repository.models.bindingModels.user.RegisterUserModel;
import com.telerik.extension_repository.models.viewModels.users.UserModelView;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void register(RegisterUserModel registationModel);
    List<UserModelView> getAll();
    EditUserModel getById(Long id);
    void  edit(EditUserModel editUserModel);
    LoggedUser getByUsernameAndPassword(String username, String password);

    LoginUser getByUsername(String username);

    @PreAuthorize("hasRole('ADMIN')")
    void delete();
}
