package com.moviles.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaAdmin {
	
	 private Integer idasistencia;
	    private Integer idusuario;
	    private String nombres;
	    private String apellidos;
	    private String estadoUsuario;
	    private LocalDate fecha;
	    private LocalTime horaentrada;
	    private String direntrada;
	    private LocalTime horasalida;
	    private String dirsalida;
	    private Double horastrabajadas;
	    private String estadoAsistencia;

}
