package com.moviles.services;

import java.util.List;

import com.moviles.dto.AsistenciaHoyResponse;
import com.moviles.dto.AsistenciaResponse;
import com.moviles.dto.DetalleSAsistenciaResponse;
import com.moviles.dto.EntradaRequest;
import com.moviles.dto.HistorialAsistenciaResponse;
import com.moviles.dto.SalidaRequest;
import com.moviles.model.Asistencia;

public interface AsistenciaService {
	
	AsistenciaResponse registrarEntrada(EntradaRequest request);

	AsistenciaResponse registrarSalida(SalidaRequest request);

	List<Asistencia> listarPorUsuario(Integer idusuario);
	
	AsistenciaHoyResponse obtenerAsistenciaHoy(Integer idusuario);
	
	List<HistorialAsistenciaResponse> listarHistorialPorUsuario(Integer idusuario);

	DetalleSAsistenciaResponse obtenerDetalleAsistencia(Integer idasistencia);
}
