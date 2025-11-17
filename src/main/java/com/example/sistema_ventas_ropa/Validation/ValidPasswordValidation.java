package com.example.sistema_ventas_ropa.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ValidPasswordValidation implements ConstraintValidator<ValidPassword, String> {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.#])[A-Za-z\\d@$!%*?&.#]{8,}$";

    @Override
    public boolean isValid(String contrasena, ConstraintValidatorContext context) {
        if (contrasena == null) {
            return false;
        }
        return contrasena.matches(PASSWORD_PATTERN);
    }
}
