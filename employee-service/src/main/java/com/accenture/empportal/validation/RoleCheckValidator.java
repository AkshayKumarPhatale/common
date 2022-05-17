package com.accenture.empportal.validation;

import com.accenture.empportal.entity.Role;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleCheckValidator implements ConstraintValidator<RoleCheck, String> {

	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		Boolean  isRoleExists=Role.contains(value);
		
		if(isRoleExists) {
			return true;
		}
		
		else {
			return false;
		}
	}

}
