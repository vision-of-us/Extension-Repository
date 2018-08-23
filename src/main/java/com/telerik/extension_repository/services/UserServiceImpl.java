package com.telerik.extension_repository.services;

import com.telerik.extension_repository.entities.Authority;
import com.telerik.extension_repository.entities.User;
import com.telerik.extension_repository.errors.Errors;
import com.telerik.extension_repository.models.bindingModels.user.EditUserModel;
import com.telerik.extension_repository.models.bindingModels.user.LoggedUser;
import com.telerik.extension_repository.models.bindingModels.user.LoginUser;
import com.telerik.extension_repository.models.bindingModels.user.RegisterUserModel;
import com.telerik.extension_repository.models.viewModels.users.UserModelView;
import com.telerik.extension_repository.repositories.AuthorityRepository;
import com.telerik.extension_repository.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserRepository userRepository;

    private AuthorityRepository roleRepository;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthorityRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

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
    public List<UserModelView> getAll() {
        List<User> users = this.userRepository.findAll();
        List<UserModelView> userModelViews = new ArrayList<>();
        for (User user : users) {
            UserModelView userModelView = this.modelMapper.map(user, UserModelView.class);
            userModelViews.add(userModelView);
        }
        return userModelViews;
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
        Optional<User> user = this.userRepository.findByUsername(username);
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

    private Set<Authority> getAuthorities(String authority) {
        Set<Authority> userAuthorities = new HashSet<>();

        userAuthorities.add(this.roleRepository.getByAuthority(authority));

        return userAuthorities;
    }

    private String getUserAuthority(String userId) {
        return this
                .userRepository
                .findById(userId)
                .get()
                .getAuthorities()
                .stream()
                .findFirst()
                .get()
                .getAuthority();
    }
}
