package com.moviles.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	
	@NotBlank(message = "El nombre es obligatorio")
	private String nombres;
	@NotBlank(message = "El apellido es obligatorio")
    private String apellidos;
	@NotBlank(message = "La direccion es obligatorio")
    private String direccion;
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Ingrese un correo válido")
    private String email;
    
    @NotBlank(message = "El telefono es obligatorio")
    private String celular;
    
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener mínimo 8 caracteres")
    private String password;

}
