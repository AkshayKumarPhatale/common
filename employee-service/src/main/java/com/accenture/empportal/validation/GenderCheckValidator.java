package com.accenture.empportal.validation;

import com.accenture.empportal.entity.Gender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderCheckValidator implements ConstraintValidator<GenderCheck, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		Boolean isGenderExists = Gender.contains(value);

		if (isGenderExists) {

			return true;
		}

		else {
			
			return false;
		}
	}

}
