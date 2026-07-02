package com.moviles.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialAsistenciaResponse {

	private Integer idasistencia;
    private LocalDate fecha;
    private LocalTime horaentrada;
    private LocalTime horasalida;
    private Double horastrabajadas;
    private String estado;
}

