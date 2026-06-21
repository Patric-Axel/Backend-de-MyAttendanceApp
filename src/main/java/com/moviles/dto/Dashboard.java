package com.moviles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dashboard {
	
	private long totalUsuarios;
    private long asistenciasHoy;
    private long tardanzasHoy;
    private long faltasHoy;

}
