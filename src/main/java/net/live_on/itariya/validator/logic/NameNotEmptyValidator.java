package net.live_on.itariya.validator.logic;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import net.live_on.itariya.validator.NameNotEmpty;

public class NameNotEmptyValidator implements ConstraintValidator<NameNotEmpty, String> {

    @Override
    public void initialize(NameNotEmpty constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
        	return false;
        }

        return true;
    }

}
