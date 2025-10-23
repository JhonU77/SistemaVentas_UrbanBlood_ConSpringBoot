package com.example.sistema_ventas_ropa.Controller;

import com.example.sistema_ventas_ropa.Entity.Ropa;
import com.example.sistema_ventas_ropa.Service.RopaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    private final RopaService ropaService;

    @Autowired
    public IndexController(RopaService ropaService) {
        this.ropaService = ropaService;
    }

    @GetMapping("/")
    public String mostrarInicio(Model model) {
        List<List<Ropa>> grupos = ropaService.obtenerRopaEnGrupos(3);
        System.out.println("GRUPOS: " + grupos);
        model.addAttribute("grupos", grupos);
        return "index";
    }   
}
