package com.example.sistema_ventas_ropa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sistema_ventas_ropa.Entity.Usuario;

@Repository
public interface LoginRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCorreoAndContrasena(String correo, String contrasena);
    Usuario findByCorreo(String correo);
}
