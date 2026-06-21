package com.moviles.services;


import com.moviles.dto.ActualizarFotoRequest;
import com.moviles.dto.ActualizarPerfilRequest;
import com.moviles.dto.LoginRequest;
import com.moviles.dto.PerfilResponse;
import com.moviles.dto.RegisterRequest;
import com.moviles.dto.SesionResponse;


public interface UsuarioService {
	
	SesionResponse login(LoginRequest request);

	SesionResponse register(RegisterRequest request);
	
	PerfilResponse actualizarPerfil(Integer idusuario, ActualizarPerfilRequest request);
	
	PerfilResponse actualizarFotoPerfil(Integer idusuario,  ActualizarFotoRequest request);

}
