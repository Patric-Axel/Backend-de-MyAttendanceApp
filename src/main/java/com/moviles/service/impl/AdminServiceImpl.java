package com.moviles.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviles.constantes.EstadoAsistencia;
import com.moviles.constantes.EstadoUsuario;
import com.moviles.dto.AsistenciaAdmin;
import com.moviles.interfaces.IAsistenciaRepository;
import com.moviles.interfaces.IUsuarioRepository;
import com.moviles.model.Asistencia;
import com.moviles.model.Usuario;
import com.moviles.services.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

		    @Autowired
		    private IAsistenciaRepository repoAsis;

		    @Autowired
		    private IUsuarioRepository repoUsu;

		    @Override
		    public List<AsistenciaAdmin> listarAsistenciasAdmin() {

		        return repoAsis.findAllByOrderByFechaDesc()
		                .stream()
		                .map(this::convertir)
		                .collect(Collectors.toList());
		    }

		    private AsistenciaAdmin convertir(Asistencia asis) {

		        Usuario usuario = repoUsu.findById(asis.getIdusuario())
		                .orElse(null);

		        return new AsistenciaAdmin(
		                asis.getIdasistencia(),
		                asis.getIdusuario(),
		                usuario != null ? usuario.getNombres() : "Sin nombre",
		                usuario != null ? usuario.getApellidos() : "Sin apellido",
		                usuario != null ? obtenerEstadoUsuario(usuario.getIdestado()) : "Desconocido",
		                asis.getFecha(),
		                asis.getHoraentrada(),
		                asis.getDirentrada(),
		                asis.getHorasalida(),
		                asis.getDirsalida(),
		                asis.getHorastrabajadas(),
		                obtenerEstadoAsistencia(asis.getIdestadoasis())
		        );
		    }

		    private String obtenerEstadoUsuario(Integer idestado) {
		        if(idestado == EstadoUsuario.ACTIVO) return "Activo";
		        if(idestado == EstadoUsuario.VACACIONES) return "Vacaciones";
		        if(idestado == EstadoUsuario.CESADO) return "Cesado";
		        return "Desconocido";
		    }

		    private String obtenerEstadoAsistencia(Integer idestadoasis) {
		        if(idestadoasis == EstadoAsistencia.TARDANZA) return "Tardanza";
		        if(idestadoasis == EstadoAsistencia.COMPLETO) return "Completo";
		        if(idestadoasis == EstadoAsistencia.PERMISO) return "Permiso";
		        if(idestadoasis == EstadoAsistencia.FALTA) return "Falta";
		        return "Desconocido";
		    }

}
