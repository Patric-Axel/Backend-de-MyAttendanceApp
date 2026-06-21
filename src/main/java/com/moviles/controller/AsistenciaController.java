package com.moviles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.moviles.dto.AsistenciaHoyResponse;
import com.moviles.dto.AsistenciaResponse;
import com.moviles.dto.EntradaRequest;
import com.moviles.dto.SalidaRequest;
import com.moviles.model.Asistencia;
import com.moviles.services.AsistenciaService;

@RestController
@RequestMapping("/api/asistencia")
@CrossOrigin("*")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @PostMapping("/entrada")
    public ResponseEntity<AsistenciaResponse> registrarEntrada(
            @RequestBody EntradaRequest request) {

        return ResponseEntity.ok(
                asistenciaService.registrarEntrada(request));
    }
    
    @PostMapping("/salida")
    public ResponseEntity<AsistenciaResponse> registrarSalida(
            @RequestBody SalidaRequest request) {

        return ResponseEntity.ok(
                asistenciaService.registrarSalida(request));
    }
    
    @GetMapping("/usuario/{idusuario}")
    public ResponseEntity<List<Asistencia>> listarPorUsuario(
            @PathVariable Integer idusuario){

        return ResponseEntity.ok(
                asistenciaService.listarPorUsuario(idusuario));
        
    }
    
    @GetMapping("/hoy/{idusuario}")
    public ResponseEntity<AsistenciaHoyResponse> obtenerAsistenciaHoy(
            @PathVariable Integer idusuario
    ) {
        return ResponseEntity.ok(
                asistenciaService.obtenerAsistenciaHoy(idusuario)
        );
    }
}