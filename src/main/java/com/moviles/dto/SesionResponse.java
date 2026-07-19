package com.moviles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SesionResponse {

    private boolean success;
    private String message;

    private Integer userId;

    private String nombres;
    private String apellidos;
    private String email;
    private String celular;
    private String direccion;
    private String fotoperfil;

    private Integer idrol;
    private Integer idestado;

}