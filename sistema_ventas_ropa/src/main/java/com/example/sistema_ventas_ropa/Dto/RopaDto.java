package com.example.sistema_ventas_ropa.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RopaDto {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio.")
    //@UniqueNamePlato
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria.")
    private String descripcion;

    @NotBlank(message = "El precio es obligatorio.")
    //@PrecioNumber
    private String precio;

    @NotBlank(message = "La URL de la imagen es obligatoria.")
    private String imagenURL;
}
