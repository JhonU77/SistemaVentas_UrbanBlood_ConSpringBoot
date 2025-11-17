package com.example.sistema_ventas_ropa.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sistema_ventas_ropa.Entity.Usuario;
import com.example.sistema_ventas_ropa.Repository.LoginRepository;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public Usuario autenticarUsuario(String correo, String contrasena) {
        return loginRepository.findByCorreoAndContrasena(correo, contrasena);
    }

    public Usuario buscarPorCorreo(String correo) {
        return loginRepository.findByCorreo(correo);
    }

}