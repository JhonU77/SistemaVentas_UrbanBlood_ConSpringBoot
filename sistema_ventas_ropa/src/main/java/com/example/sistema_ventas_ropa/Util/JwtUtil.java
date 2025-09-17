package com.example.sistema_ventas_ropa.Util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expirationMs}")
    private int expirationMs;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // Generar token con rol
    public String generarToken(UserDetails userDetails) {
        List<String> roles = userDetails.getAuthorities().stream()
            .map(auth -> auth.getAuthority())
            .toList();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Obtener correo desde el token
    public String obtenerCorreo(String token) {
        return getClaims(token).getSubject();
    }

    // Obtener rol desde el token
    public String obtenerRol(String token) {
        return getClaims(token).get("rol", String.class);
    }

    public List<String> obtenerRoles(String token) {
        return getClaims(token).get("roles", List.class);
    }


    // Validar si el token es válido y no expiró
    public boolean validarToken(String token, UserDetails userDetails) {
        final String correo = obtenerCorreo(token);
        return correo.equals(userDetails.getUsername()) && !isTokenExpirado(token);
    }

    private boolean isTokenExpirado(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}