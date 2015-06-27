package co.adun.mvnejb3jpa.web.validation;

import org.springframework.validation.Errors;

public interface PageModelValidator<T> {
    void validate(T[] model, Errors errors);
}
