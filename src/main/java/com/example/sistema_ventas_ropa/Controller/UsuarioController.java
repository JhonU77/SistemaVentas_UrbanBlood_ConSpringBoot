package com.example.sistema_ventas_ropa.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sistema_ventas_ropa.Dto.RegistrarUsuarioDto;
import com.example.sistema_ventas_ropa.Service.UsuarioService;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model,
                                            @RequestParam(value = "exito", required = false) String exito) {
        model.addAttribute("usuario", new RegistrarUsuarioDto());
        if (exito != null) {
            model.addAttribute("usuarioRegistrado", true);
        }

        return "registrarUsuario";
    }

    @PostMapping("/registrar")
    public String registrarUsuario(@Valid @ModelAttribute("usuario") RegistrarUsuarioDto usuarioDto,
                                BindingResult result, Model model) {
        System.out.println("Datos recibidos: " + usuarioDto.getCorreo());

        if (!usuarioDto.getContrasena().equals(usuarioDto.getConfirmarContrasena())) {
            System.out.println("Contraseñas no coinciden");
            result.rejectValue("confirmarContrasena", "error.confirmarContrasena", "Las contraseñas no coinciden");
        }

        if (result.hasErrors()) {
            System.out.println("Errores de validación: " + result.getAllErrors());
            return "registrarUsuario";
        }

        String encriptada = passwordEncoder.encode(usuarioDto.getContrasena());
        usuarioDto.setContrasena(encriptada);

        usuarioService.Registrar(usuarioDto);

        return "redirect:/registro?exito";
    }

}