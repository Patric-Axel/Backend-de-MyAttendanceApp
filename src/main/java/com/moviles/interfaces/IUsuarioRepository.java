package com.moviles.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviles.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByEmail(String email);
}
