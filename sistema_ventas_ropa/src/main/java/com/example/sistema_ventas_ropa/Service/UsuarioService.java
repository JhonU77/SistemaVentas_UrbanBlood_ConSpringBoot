package com.example.sistema_ventas_ropa.Service;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.sistema_ventas_ropa.Dto.RegistrarUsuarioDto;
import com.example.sistema_ventas_ropa.Entity.Usuario;
import com.example.sistema_ventas_ropa.Repository.UsuarioRepository;
import java.util.Collections;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void Registrar(RegistrarUsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellidoPaterno(dto.getApellidoPaterno());
        usuario.setApellidoMaterno(dto.getApellidoMaterno());
        usuario.setCelular(dto.getCelular());
        usuario.setCorreo(dto.getCorreo());
        usuario.setRol("USER");
        usuario.setContrasena(dto.getContrasena());
        usuarioRepository.save(usuario);
        System.out.println("Intentando registrar usuario: " + dto.getCorreo());

    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + correo));

        return new User(usuario.getCorreo(), usuario.getContrasena(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toUpperCase())));

    }
    
}