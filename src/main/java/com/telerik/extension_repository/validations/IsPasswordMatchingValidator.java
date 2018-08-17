package com.telerik.extension_repository.validations;


import com.telerik.extension_repository.annotations.IsPasswordMatching;
import com.telerik.extension_repository.models.bindingModels.RegisterUserModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordMatchingValidator implements ConstraintValidator<IsPasswordMatching,Object> {
    @Override
    public void initialize(IsPasswordMatching isPasswordMatching) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object instanceof RegisterUserModel){
            RegisterUserModel userModel = (RegisterUserModel) object;
            return userModel.getPassword().equals(userModel.getConfirmPassword());
        }
        return false;
    }
}
