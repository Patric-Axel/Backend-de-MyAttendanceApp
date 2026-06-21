package com.moviles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarPerfilRequest {

	   	private String nombres;
	    private String apellidos;
	    private String direccion;
	    private String celular;
}
