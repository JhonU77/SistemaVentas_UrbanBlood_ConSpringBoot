package com.example.sistema_ventas_ropa.Controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.sistema_ventas_ropa.Dto.RegistrarUsuarioDto;
import com.example.sistema_ventas_ropa.Service.UsuarioService;
import jakarta.validation.Valid;

@RestController
public class UsuarioApiController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/api/registrar")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody RegistrarUsuarioDto dto) {
        // Forzar el rol USER por defecto
        dto.setRol("USER");
        usuarioService.Registrar(dto);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Usuario registrado correctamente");
        return ResponseEntity.ok(respuesta);
    }

    
}