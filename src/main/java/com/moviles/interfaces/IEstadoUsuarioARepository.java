package com.moviles.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moviles.model.EstadoAsistenciaE;


@Repository
public interface IEstadoUsuarioARepository extends JpaRepository<EstadoAsistenciaE, Integer>{

}
