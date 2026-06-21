package com.moviles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviles.dto.ActualizarFotoRequest;
import com.moviles.dto.ActualizarPerfilRequest;
import com.moviles.dto.PerfilResponse;
import com.moviles.services.UsuarioService;

@RestController
@RequestMapping("/api/actualizar")
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService repoUsu;
	
	@PutMapping("/{idusuario}")
	public ResponseEntity<PerfilResponse> actualizarPerfil(
	        @PathVariable Integer idusuario,
	        @RequestBody ActualizarPerfilRequest request){

	    return ResponseEntity.ok(
	    		repoUsu.actualizarPerfil(
	                    idusuario,
	                    request));
	}
	
	
	@PatchMapping("/{idusuario}/foto")
	public ResponseEntity<PerfilResponse> actualizaFotoPerfil( 
			@PathVariable Integer idusuario,
	        @RequestBody ActualizarFotoRequest request
			) {
		
		return ResponseEntity.ok(
				repoUsu.actualizarFotoPerfil(
						idusuario, 
						request));
	}

}
