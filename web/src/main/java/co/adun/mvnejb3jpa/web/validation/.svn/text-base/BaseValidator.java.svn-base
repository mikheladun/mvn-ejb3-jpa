package co.adun.mvnejb3jpa.web.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class BaseValidator<T> implements PageModelValidator<T>, Validator {

    @Override
    public void validate(Object target, Errors errors) {
	T t = (T) target;
	validate(t, errors);
    }

    public void validate(T[] models, Errors errors) {
	for (T model : models) {
	    validate(model, errors);
	}
    }
}