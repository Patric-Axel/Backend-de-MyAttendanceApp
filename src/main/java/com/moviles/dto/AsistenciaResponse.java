package com.moviles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaResponse {

	private Boolean succes;
	private String message;
	private Integer idasistencia;
}
