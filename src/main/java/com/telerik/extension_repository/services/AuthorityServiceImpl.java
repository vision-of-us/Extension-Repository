package com.telerik.extension_repository.services;


import org.modelmapper.ModelMapper;

import com.telerik.extension_repository.entities.Authority;
import com.telerik.extension_repository.models.bindingModels.AuthorityModel;
import com.telerik.extension_repository.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AuthorityModel> getAll() {
        List<Authority> roles = this.roleRepository.findAll();
        List<AuthorityModel> roleModels = new ArrayList<>();
        for (Authority role : roles) {
            AuthorityModel roleModel = this.modelMapper.map(role, AuthorityModel.class);
            roleModels.add(roleModel);
        }
        return roleModels;
    }

    @Override
    public Set<AuthorityModel> getAllByName(String[] names) {
        Set<Authority> roles = this.roleRepository.findAllByAuthorityIn(names);
        Set<AuthorityModel> roleModels = new HashSet<>();
        for (Authority role : roles) {
            AuthorityModel roleModel = this.modelMapper.map(role, AuthorityModel.class);
            roleModels.add(roleModel);
        }
        return roleModels;
    }
}
