package com.example.sistema_ventas_ropa.Validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.sistema_ventas_ropa.Repository.RopaRepository;

@Component
public class UniqueNameRopaValidation implements ConstraintValidator<UniqueNameRopa, String> {

    @Autowired
    private RopaRepository ropaRepository;

    @Override
    public boolean isValid(String nombre, ConstraintValidatorContext context) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return true;
        }
        return !ropaRepository.existsByNombre(nombre);
    }
}
