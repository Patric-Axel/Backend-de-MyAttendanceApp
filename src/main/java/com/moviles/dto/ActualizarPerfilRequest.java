package com.moviles.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActualizarPerfilRequest {

    @NotBlank(message = "El celular es obligatorio")
    @Pattern(
        regexp = "\\d{9}",
        message = "El celular debe contener 9 dígitos"
    )
    private String celular;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;
}