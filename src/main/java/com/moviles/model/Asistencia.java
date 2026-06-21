package com.moviles.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_asistencia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asistencia {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	    private Integer idestadoasis;
	    private Integer idusuario;
	    private String observacion;

	    @PrePersist
	    public void prePersist() {
	        fecha = LocalDate.now();
	        fecharegistro = LocalDateTime.now();
	    }
	    
	    
	    @ManyToOne
	    @JoinColumn(name = "idestadoasis", insertable = false, updatable = false)
	    private EstadoAsistenciaE objEstadoAsis;

	    @ManyToOne
	    @JoinColumn(name = "idusuario", insertable = false, updatable = false)
	    private Usuario objUsuario;
}
