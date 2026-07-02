package com.moviles.interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviles.model.Asistencia;

public interface IAsistenciaRepository extends JpaRepository<Asistencia, Integer>{

	Optional<Asistencia> findByIdusuarioAndFecha(
            Integer idusuario,
            LocalDate fecha);
	
	List<Asistencia> findByIdusuarioOrderByFechaDesc(Integer idusuario);

    List<Asistencia> findByIdusuario(
            Integer idusuario);
    
    List<Asistencia> findByFechaAndHorasalidaIsNull(
            LocalDate fecha);
    
    List<Asistencia> findAllByOrderByFechaDesc();
    
    long countByFecha(LocalDate fecha);

    long countByFechaAndIdestadoasis(
            LocalDate fecha,
            Integer idestadoasis);
}
