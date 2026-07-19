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
import com.moviles.dto.ActualizarPasswordRequest;
import com.moviles.dto.ActualizarPerfilRequest;
import com.moviles.dto.PerfilResponse;
import com.moviles.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/actualizar")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService repoUsu;

    // Actualizar únicamente celular y dirección
    @PutMapping("/{idusuario}")
    public ResponseEntity<PerfilResponse> actualizarPerfil(
            @PathVariable Integer idusuario,
            @Valid @RequestBody ActualizarPerfilRequest request) {

        return ResponseEntity.ok(
                repoUsu.actualizarPerfil(
                        idusuario,
                        request
                )
        );
    }

    // Actualizar foto de perfil
    @PatchMapping("/{idusuario}/foto")
    public ResponseEntity<PerfilResponse> actualizarFotoPerfil(
            @PathVariable Integer idusuario,
            @Valid @RequestBody ActualizarFotoRequest request) {

        return ResponseEntity.ok(
                repoUsu.actualizarFotoPerfil(
                        idusuario,
                        request
                )
        );
    }

    // Actualizar contraseña
    @PatchMapping("/{idusuario}/password")
    public ResponseEntity<PerfilResponse> actualizarPassword(
            @PathVariable Integer idusuario,
            @Valid @RequestBody ActualizarPasswordRequest request) {

        return ResponseEntity.ok(
                repoUsu.actualizarPassword(
                        idusuario,
                        request
                )
        );
    }
}