package com.example.sistema_ventas_ropa.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueNameRopaValidation.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueNameRopa {

    String message() default "El nombre de esta prenda ya está registrado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
