package com.moviles.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleSAsistenciaResponse {

    private Integer idasistencia;
    private LocalDate fecha;
    private LocalDateTime fecharegistro;
    
    private LocalTime horaentrada;
    private LocalTime horasalida;
    private Double horastrabajadas;
    
    private Double latitudentrada;
    private Double longitudentrada;
    
    private Double latitudsalida;
    private Double longitudsalida;
    
    private String direntrada;
    private String dirsalida;

    private String fotoentrada;
    private String fotosalida;

    private String estado;
    private String observacion;
}
