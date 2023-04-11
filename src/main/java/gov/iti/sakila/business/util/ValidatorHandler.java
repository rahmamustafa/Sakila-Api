package gov.iti.sakila.business.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import lombok.NonNull;

import java.util.Set;
import java.util.stream.Collectors;


public class ValidatorHandler {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final ValidatorHandler instance = new ValidatorHandler();

    public static ValidatorHandler getInstance() {
        return instance;
    }
    private ValidatorHandler(){}

    public <T> boolean isValid(@NonNull T obj){
        Set<ConstraintViolation<T>> violations = factory.getValidator().validate(obj);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException(violations.stream()
                                                         .map(e -> e.getMessage())
                                                         .collect(Collectors.joining(", ")));
        }
        return true;
    }

}
