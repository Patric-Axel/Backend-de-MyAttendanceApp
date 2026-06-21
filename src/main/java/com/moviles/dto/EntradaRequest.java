package com.moviles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntradaRequest {
	
	private Integer idusuario;
	private Double latitudentrada;
	private Double longitudentrada;
	private String direntrada;
	private String fotoentrada;

}
