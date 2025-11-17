package com.example.sistema_ventas_ropa.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.example.sistema_ventas_ropa.Dto.LoginRequest;
import com.example.sistema_ventas_ropa.Service.UsuarioService;
import com.example.sistema_ventas_ropa.Util.JwtUtil;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasena())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generarToken(userDetails);
            String rol = userDetails.getAuthorities().iterator().next().getAuthority();

            System.out.println("Usuario: " + userDetails.getUsername());
            System.out.println("Rol: " + rol);
            System.out.println("Token JWT generado: " + token);

            return ResponseEntity.ok(Map.of("token", token, "rol", rol));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciales inválidas"));
        }
    }

}
