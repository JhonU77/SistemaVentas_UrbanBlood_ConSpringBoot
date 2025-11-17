package com.example.sistema_ventas_ropa.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PrecioNumberValidation.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PrecioNumber {
    String message() default "El precio debe ser un número válido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
