package com.example.sistema_ventas_ropa.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BienvenidaController {
    @GetMapping("/Bienvenida")
    public String MostrarBienvenida(){
        return "bienvenida";
    }
}
