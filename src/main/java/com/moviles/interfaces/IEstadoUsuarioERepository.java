package com.moviles.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moviles.model.EstadoUsuarioE;

@Repository
public interface IEstadoUsuarioERepository extends JpaRepository<EstadoUsuarioE, Integer> {

}
