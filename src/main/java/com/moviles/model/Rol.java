package com.moviles.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
	
	@Id
	private Integer idrol;
	private String descripcion;

}
