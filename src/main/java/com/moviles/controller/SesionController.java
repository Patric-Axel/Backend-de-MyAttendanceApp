package com.moviles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.moviles.dto.SesionResponse;
import com.moviles.dto.LoginRequest;
import com.moviles.dto.RegisterRequest;
import com.moviles.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class SesionController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<SesionResponse> login(
           @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                usuarioService.login(request)
        );
    }

    @PostMapping("/register")
    public ResponseEntity<SesionResponse> register(
           @Valid @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(
                usuarioService.register(request)
        );
    }
}
