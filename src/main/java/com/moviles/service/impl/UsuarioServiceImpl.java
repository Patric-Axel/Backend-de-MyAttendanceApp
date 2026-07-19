package com.moviles.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.moviles.constantes.EstadoUsuario;
import com.moviles.dto.ActualizarFotoRequest;
import com.moviles.dto.ActualizarPasswordRequest;
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

	    Optional<Usuario> usuarioOpt =
	            RepoUsu.findByEmail(request.getEmail());

	    if (usuarioOpt.isEmpty()) {

	        return new SesionResponse(
	                false,
	                "Usuario no encontrado",
	                null,
	                null,
	                null,
	                null,
	                null,
	                null,
	                null,
	                null,
	                null
	        );
	    }

	    Usuario usuario = usuarioOpt.get();

	    if (!passwordEncoder.matches(
	            request.getPassword(),
	            usuario.getPwd())) {

	        return new SesionResponse(
	                false,
	                "Contraseña incorrecta",
	                null,
	                null,
	                null,
	                null,
	                null,
	                null,
	                null,
	                null,
	                null
	        );
	    }

	    if (usuario.getIdestado() == EstadoUsuario.CESADO) {

	        return new SesionResponse(
	                false,
	                "Usuario ha sido cesado",
	                null,
	                null,
	                null,
	                null,
	                null,
	                null,
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
	            usuario.getApellidos(),
	            usuario.getEmail(),
	            usuario.getCelular(),
	            usuario.getDireccion(),
	            usuario.getFotoperfil(),
	            usuario.getIdrol(),
	            usuario.getIdestado()
	    );
	}

	@Override
	public SesionResponse register(RegisterRequest request) {

	    Optional<Usuario> usuarioExistente =
	            RepoUsu.findByEmail(request.getEmail());

	    if (usuarioExistente.isPresent()) {

	        return new SesionResponse(
	                false,
	                "El correo ya está registrado",
	                null,
	                null,
	                null,
	                null,
	                null,
	                null,
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

	    usuario.setPwd(
	            passwordEncoder.encode(request.getPassword())
	    );

	    usuario.setFotoperfil(null);
	    usuario.setIdestado(1);
	    usuario.setIdrol(2);

	    RepoUsu.save(usuario);

	    return new SesionResponse(
	            true,
	            "Usuario registrado correctamente",
	            usuario.getIdusuario(),
	            usuario.getNombres(),
	            usuario.getApellidos(),
	            usuario.getEmail(),
	            usuario.getCelular(),
	            usuario.getDireccion(),
	            usuario.getFotoperfil(),
	            usuario.getIdrol(),
	            usuario.getIdestado()
	    );
	}

	@Override
	public PerfilResponse actualizarPerfil(
	        Integer idusuario,
	        ActualizarPerfilRequest request) {

	    Usuario usuario = RepoUsu
	            .findById(idusuario)
	            .orElseThrow(() ->
	                    new RuntimeException("Usuario no encontrado"));

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
	            usuario.getFotoperfil()
	    );
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

	@Override
	public PerfilResponse actualizarPassword(
	        Integer idusuario,
	        ActualizarPasswordRequest request) {

	    Usuario usuario = RepoUsu.findById(idusuario)
	            .orElseThrow(() ->
	                    new RuntimeException("Usuario no encontrado"));

	    if (!passwordEncoder.matches(
	            request.getPasswordActual(),
	            usuario.getPwd())) {

	        throw new RuntimeException(
	                "La contraseña actual es incorrecta"
	        );
	    }

	    if (!request.getPasswordNueva()
	            .equals(request.getConfirmarPassword())) {

	        throw new RuntimeException(
	                "Las contraseñas no coinciden"
	        );
	    }

	    if (passwordEncoder.matches(
	            request.getPasswordNueva(),
	            usuario.getPwd())) {

	        throw new RuntimeException(
	                "La nueva contraseña debe ser diferente"
	        );
	    }

	    usuario.setPwd(
	            passwordEncoder.encode(
	                    request.getPasswordNueva()
	            )
	    );

	    RepoUsu.save(usuario);

	    return new PerfilResponse(
	            usuario.getIdusuario(),
	            usuario.getNombres(),
	            usuario.getApellidos(),
	            usuario.getDireccion(),
	            usuario.getEmail(),
	            usuario.getCelular(),
	            usuario.getFotoperfil()
	    );
	}
	
	
}

