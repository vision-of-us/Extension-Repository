package com.telerik.extension_repository.services;

import com.telerik.extension_repository.models.bindingModels.EditUserModel;
import com.telerik.extension_repository.models.bindingModels.RegisterUserModel;
import com.telerik.extension_repository.models.viewModels.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public void persist(RegisterUserModel userModel) {

    }

    @Override
    public List<UserModel> getAll() {
        return null;
    }

    @Override
    public EditUserModel getById(Long id) {
        return null;
    }

    @Override
    public void edit(EditUserModel editUserModel) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
