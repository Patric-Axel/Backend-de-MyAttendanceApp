package com.moviles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SesionResponse {
	
	private boolean success;
    private String message;
    private Integer userId;
    private String nombres;
    private Integer idrol;

}
