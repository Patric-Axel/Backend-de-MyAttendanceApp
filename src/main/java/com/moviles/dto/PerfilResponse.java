package com.moviles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilResponse {

	private Integer idusuario;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String email;
    private String celular;
    private String fotoperfil;
}
