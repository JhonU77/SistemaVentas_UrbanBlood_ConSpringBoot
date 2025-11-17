package com.example.sistema_ventas_ropa.Util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.sistema_ventas_ropa.Service.UsuarioService;

import java.util.List;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.equals("/registrar") || path.equals("/registro") || path.startsWith("/css") || path.startsWith("/js") || path.startsWith("/images")) {
            filterChain.doFilter(request, response);
            return;
        }
        String authHeader = request.getHeader("Authorization");

        System.out.println("Authorization header: " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String correo = jwtUtil.obtenerCorreo(token);

            System.out.println("Correo extraído del token: " + correo);

            if (correo != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = usuarioService.loadUserByUsername(correo);
                if (jwtUtil.validarToken(token, userDetails)) {
                    List<String> roles = jwtUtil.obtenerRoles(token);
                    List<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(rol -> rol.startsWith("ROLE_") ? rol : "ROLE_" + rol)
                        .map(SimpleGrantedAuthority::new)
                        .toList();
                    UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
                else {
                    System.out.println("Token inválido o expirado.");
                }
            }
        } else {
            System.out.println("No se encontró el token o el formato es incorrecto.");
        }

        filterChain.doFilter(request, response);
    }

}
