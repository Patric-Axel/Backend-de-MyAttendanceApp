package com.moviles.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="tb_estado_usu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoUsuarioE {
	
	@Id
	private Integer idestado;
	private String descripcion;

}
