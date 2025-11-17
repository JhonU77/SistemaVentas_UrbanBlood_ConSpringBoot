package com.example.sistema_ventas_ropa.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.sistema_ventas_ropa.Repository.UsuarioRepository;

@Component
public class UniqueEmailValidation implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean isValid(String correo, ConstraintValidatorContext context) {
        if (correo == null || correo.isEmpty()) {
            return true;
        }
        return !usuarioRepository.existsByCorreo(correo);
    }
}
