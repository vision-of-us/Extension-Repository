package com.telerik.extension_repository.services;

import com.telerik.extension_repository.models.bindingModels.user.AuthorityModel;

import java.util.List;
import java.util.Set;

public interface AuthorityService {
    List<AuthorityModel> getAll();
    Set<AuthorityModel> getAllByName(String[] names);
}
