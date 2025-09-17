package com.example.sistema_ventas_ropa.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarUsuarioDto {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido paterno es obligatorio")
    private String apellidoPaterno;

    @NotBlank(message = "El apellido materno es obligatorio")
    private String apellidoMaterno;

    @NotBlank(message = "El número de celular es obligatorio")
    private String celular;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no es válido")
    //@UniqueEmail
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    //@ValidPassword
    private String contrasena;

    @NotBlank(message = "Vuelva ingresar su contraseña")
    private String confirmarContrasena;

    //@NotBlank(message = "Debe seleccionar un rol")
    private String rol;
}