package com.moviles.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.moviles.constantes.EstadoUsuario;
import com.moviles.dto.ActualizarFotoRequest;
import com.moviles.dto.ActualizarPerfilRequest;
import com.moviles.dto.LoginRequest;
import com.moviles.dto.PerfilResponse;
import com.moviles.dto.RegisterRequest;
import com.moviles.dto.SesionResponse;
import com.moviles.interfaces.IUsuarioRepository;
import com.moviles.model.Usuario;
import com.moviles.services.UsuarioService;



@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private IUsuarioRepository RepoUsu;
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	

	@Override
	public SesionResponse login(LoginRequest request) {
		// TODO Auto-generated method stub
		Optional<Usuario> usuarioOpt = RepoUsu.findByEmail(request.getEmail());
		
        if(usuarioOpt.isEmpty()) {

            return new SesionResponse (
                    false,
                    "Usuario no encontrado",
                    null,
                    null,
                    null
                    
       
            );
        }

        Usuario usuario = usuarioOpt.get();

        if(!passwordEncoder.matches(
                request.getPassword(),
                usuario.getPwd())) {

            return new SesionResponse(
                    false,
                    "Contraseña incorrecta",
                    null,
                    null,
                    null
            );
        }

        if(usuario.getIdestado() == EstadoUsuario.CESADO) {

            return new SesionResponse(
                    false,
                    "Usuario ha sido Cesado",
                    null,
                    null,
                    null
            );
        }

        return new SesionResponse(
                true,
                "Login exitoso",
                usuario.getIdusuario(),
                usuario.getNombres(),
                usuario.getIdrol()
        );
    }

	@Override
	public SesionResponse register(RegisterRequest request) {
		// TODO Auto-generated method stub
		Optional<Usuario> usuarioExistente =
				RepoUsu.findByEmail(
                        request.getEmail());

        if(usuarioExistente.isPresent()) {

            return new SesionResponse(
                    false,
                    "El correo ya está registrado",
                    null,
                    null,
                    null
            );
        }

        Usuario usuario = new Usuario();

        usuario.setNombres(request.getNombres());
        usuario.setApellidos(request.getApellidos());
        usuario.setDireccion(request.getDireccion());
        usuario.setEmail(request.getEmail());
        usuario.setCelular(request.getCelular());

        // BCrypt
        usuario.setPwd(passwordEncoder.encode(request.getPassword()));

        usuario.setFotoperfil(null);

        // Activo
        usuario.setIdestado(1);

        // Empleado
        usuario.setIdrol(2);

        RepoUsu.save(usuario);

        return new SesionResponse(
                true,
                "Usuario registrado correctamente",
                usuario.getIdusuario(),
                usuario.getNombres(),
                usuario.getIdrol()
        );
    }

	@Override
	public PerfilResponse actualizarPerfil(Integer idusuario, ActualizarPerfilRequest request) {
		// TODO Auto-generated method stub
		Usuario usuario = RepoUsu
	            .findById(idusuario)
	            .orElseThrow(() ->
	                    new RuntimeException("Usuario no encontrado"));

	    usuario.setNombres(
	            request.getNombres());

	    usuario.setApellidos(
	            request.getApellidos());

	    usuario.setDireccion(
	            request.getDireccion());

	    usuario.setCelular(
	            request.getCelular());

	    RepoUsu.save(usuario);

	    return new PerfilResponse(
	            usuario.getIdusuario(),
	            usuario.getNombres(),
	            usuario.getApellidos(),
	            usuario.getDireccion(),
	            usuario.getEmail(),
	            usuario.getCelular(),
	            usuario.getFotoperfil());
	}

	@Override
	public PerfilResponse actualizarFotoPerfil(Integer idusuario, ActualizarFotoRequest request) {
		// TODO Auto-generated method stub
		Usuario usuario = RepoUsu
	            .findById(idusuario)
	            .orElseThrow(() ->
	                    new RuntimeException("Usuario no encontrado"));

	    usuario.setFotoperfil(
	            request.getFotoperfil());

	    RepoUsu.save(usuario);

	    return new PerfilResponse(
	            usuario.getIdusuario(),
	            usuario.getNombres(),
	            usuario.getApellidos(),
	            usuario.getDireccion(),
	            usuario.getEmail(),
	            usuario.getCelular(),
	            usuario.getFotoperfil());
	}
	
	
}

