package com.moviles.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	
	@NotBlank(message = "El correo es obligatorio")
    @Email(message = "Correo inválido")
	private String email;
	
	@NotBlank(message = "La contraseña es obligatoria")
    private String password;

}
