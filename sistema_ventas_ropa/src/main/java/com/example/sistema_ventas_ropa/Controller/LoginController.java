package com.example.sistema_ventas_ropa.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.sistema_ventas_ropa.Dto.LoginRequest;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }
}
