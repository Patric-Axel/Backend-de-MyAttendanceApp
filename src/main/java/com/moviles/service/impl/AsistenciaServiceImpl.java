package com.moviles.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviles.constantes.EstadoAsistencia;
import com.moviles.constantes.EstadoUsuario;
import com.moviles.dto.AsistenciaHoyResponse;
import com.moviles.dto.AsistenciaResponse;
import com.moviles.dto.EntradaRequest;
import com.moviles.dto.SalidaRequest;
import com.moviles.interfaces.IAsistenciaRepository;
import com.moviles.interfaces.IUsuarioRepository;
import com.moviles.model.Asistencia;
import com.moviles.model.Usuario;
import com.moviles.services.AsistenciaService;

@Service
public class AsistenciaServiceImpl implements AsistenciaService{
	
	@Autowired
	private IAsistenciaRepository repoAsis;
	@Autowired
	private IUsuarioRepository repoUsu;
	
	@Override
	public AsistenciaResponse registrarEntrada(EntradaRequest request) {
		// TODO Auto-generated method stub
		
		// Validar usuario
	    Optional<Usuario> usuarioOpt = repoUsu.findById(request.getIdusuario());

	    if(usuarioOpt.isEmpty()) {

	        return new AsistenciaResponse(
	                false,
	                "Usuario no existe",
	                null);
	    }

	    Usuario usuario = usuarioOpt.get();

	    if(usuario.getIdestado() != EstadoUsuario.ACTIVO) {

	        return new AsistenciaResponse(
	                false,
	                "El usuario no puede registrar asistencia",
	                null);
	    }
		
		
		Optional<Asistencia> existeAsis = repoAsis.findByIdusuarioAndFecha(
				request.getIdusuario(), LocalDate.now());
		
		//solo si ya registro su entrada
		if(existeAsis.isPresent()) {
			return new AsistenciaResponse(
					false,
					"Ya registraste tu asistencia el día de hoy",
					null);
		}
		
		
		// si su registro es despues de las 12 media dia, se le considera falta automatica
		//if(LocalTime.now().isAfter(LocalTime.of(23, 59)))
		//hora de pruebas
		if(LocalTime.now().isAfter(LocalTime.of(12, 0))){

		    Asistencia falta = new Asistencia();

		    falta.setIdusuario(
		            request.getIdusuario());

		    falta.setFecha(
		            LocalDate.now());

		    falta.setIdestadoasis(EstadoAsistencia.FALTA);

		    falta.setHorastrabajadas(0.0);

		    repoAsis.save(falta);

		    return new AsistenciaResponse(
		            false,
		            "Horario de ingreso excedido. Se registró una falta.",
		            falta.getIdasistencia());
		}
		
		
		Asistencia asis = new Asistencia();
		
		asis.setIdusuario(request.getIdusuario());
		asis.setHoraentrada(LocalTime.now());
		asis.setLatitudentrada(request.getLatitudentrada());
		asis.setLongitudentrada(request.getLongitudentrada());
		asis.setDirentrada(request.getDirentrada());
		asis.setFotoentrada(request.getFotoentrada());
        asis.setFecha(LocalDate.now());
		
        
        
        //tardanza despues de las 8
        if(LocalTime.now().isAfter(
                LocalTime.of(8, 0))) {

        	asis.setIdestadoasis(EstadoAsistencia.TARDANZA);

        } else {

        	asis.setIdestadoasis(EstadoAsistencia.COMPLETO);
        }

        repoAsis.save(asis);

        return new AsistenciaResponse(
                true,
                "Entrada registrada correctamente",
                asis.getIdasistencia());
		
	}

	@Override
	public AsistenciaResponse registrarSalida(SalidaRequest request) {
		// TODO Auto-generated method stub
		
		Optional<Asistencia> asistenciaOpt =
				repoAsis.findByIdusuarioAndFecha(
	                    request.getIdusuario(),
	                    LocalDate.now());

	    // RN02 verificar una entrada
	    if(asistenciaOpt.isEmpty()) {

	        return new AsistenciaResponse(
	                false,
	                "Debe registrar una entrada antes de registrar la salida.",
	                null);
	    }

	    Asistencia asistencia = asistenciaOpt.get();

	    // no permite salida si existe falta
	    if(asistencia.getIdestadoasis() == EstadoAsistencia.FALTA) {

	        return new AsistenciaResponse(
	                false,
	                "El usuario tiene una falta registrada para hoy.",
	                asistencia.getIdasistencia());
	    }

	    // no se permite registrar salida mas de 1 vez
	    if(asistencia.getHorasalida() != null) {

	        return new AsistenciaResponse(
	                false,
	                "La salida ya fue registrada.",
	                asistencia.getIdasistencia());
	    }

	    // Registrar salida
	    LocalTime horaSalida = LocalTime.now();

	    asistencia.setHorasalida(horaSalida);
	    asistencia.setLatitudsalida(request.getLatitudsalida());
	    asistencia.setLongitudsalida(request.getLongitudsalida());
	    asistencia.setDirsalida(request.getDirsalida());
	    asistencia.setFotosalida(request.getFotosalida());

	    // RN06 horas trabajadas
	    Duration duracion =
	            Duration.between(
	                    asistencia.getHoraentrada(),
	                    horaSalida);

	    double horasTrabajadas =
	            duracion.toMinutes() / 60.0;

	    asistencia.setHorastrabajadas(
	            Math.round(horasTrabajadas * 100.0) / 100.0);

	    repoAsis.save(asistencia);

	    return new AsistenciaResponse(
	            true,
	            "Salida registrada correctamente.",
	            asistencia.getIdasistencia());
	
	
	}

	@Override
	public List<Asistencia> listarPorUsuario(Integer idusuario) {
		// TODO Auto-generated method stub
		return repoAsis
	            .findByIdusuario(idusuario);
	}
	
	@Override
	public AsistenciaHoyResponse obtenerAsistenciaHoy(Integer idusuario) {

	    Optional<Asistencia> asistenciaOpt =
	            repoAsis.findByIdusuarioAndFecha(
	                    idusuario,
	                    LocalDate.now()
	            );

	    if (asistenciaOpt.isEmpty()) {
	        return new AsistenciaHoyResponse(
	                false,
	                "Sin asistencia registrada hoy",
	                null,
	                null,
	                null,
	                null,
	                "Sin registro"
	        );
	    }

	    Asistencia asistencia = asistenciaOpt.get();

	    return new AsistenciaHoyResponse(
	            true,
	            "Asistencia encontrada",
	            asistencia.getFecha(),
	            asistencia.getHoraentrada(),
	            asistencia.getHorasalida(),
	            asistencia.getHorastrabajadas(),
	            asistencia.getObjEstadoAsis().getDescripcion()
	    );
	}
	

}
