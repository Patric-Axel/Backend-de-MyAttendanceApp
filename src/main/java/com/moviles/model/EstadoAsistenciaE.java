package com.moviles.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_estado_asistencia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoAsistenciaE {

	@Id
	private Integer idestadoasis;
	private String descripcion;
}
