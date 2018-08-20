package com.telerik.extension_repository.services;

import com.telerik.extension_repository.entities.User;
import com.telerik.extension_repository.errors.Errors;
import com.telerik.extension_repository.models.bindingModels.EditUserModel;
import com.telerik.extension_repository.models.bindingModels.LoggedUser;
import com.telerik.extension_repository.models.bindingModels.LoginUser;
import com.telerik.extension_repository.models.bindingModels.RegisterUserModel;
import com.telerik.extension_repository.models.viewModels.UserModel;
import com.telerik.extension_repository.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void register(RegisterUserModel registrationModel) {
        User user = this.modelMapper.map(registrationModel, User.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(registrationModel.getPassword());
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
        this.userRepository.save(user);
    }

    @Override
    public List<UserModel> getAll() {
        List<User> users = this.userRepository.findAll();
        List<UserModel> userModels = new ArrayList<>();
        for (User user : users) {
            UserModel userModel = this.modelMapper.map(user, UserModel.class);
            userModels.add(userModel);
        }
        return userModels;
    }

    @Override
    public EditUserModel getById(Long id) {
        return null;
    }

    @Override
    public void edit(EditUserModel editUserModel) {

    }

    @Override
    public LoggedUser getByUsernameAndPassword(String username, String password) {
        User user = this.userRepository.findByUsernameAndPassword(username,password);
        ModelMapper modelMapper = new ModelMapper();
        LoggedUser loggedUser = null;
        if (user != null) {
            loggedUser = modelMapper.map(user,LoggedUser.class);
        }

        return loggedUser;
    }

    @Override
    public LoginUser getByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        ModelMapper modelMapper = new ModelMapper();
        LoginUser loginUser = null;
        if (user != null){
            loginUser = modelMapper.map(user, LoginUser.class);
        }
        return loginUser;
    }

    @Override
    public void delete() {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(Errors.INVALID_CREDENTIALS);
        }

        return user;
    }
}
