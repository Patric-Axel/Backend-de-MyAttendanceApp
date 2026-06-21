package com.moviles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalidaRequest {

	private Integer idusuario;
	private Double latitudsalida;
	private Double longitudsalida;
	private String dirsalida;
	private String fotosalida;

}
