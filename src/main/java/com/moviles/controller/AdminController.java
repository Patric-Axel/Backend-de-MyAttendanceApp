package com.moviles.controller;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.moviles.constantes.Roles;
import com.moviles.dto.Dashboard;
import com.moviles.dto.LoginRequest;
import com.moviles.dto.SesionResponse;
import com.moviles.interfaces.IAsistenciaRepository;
import com.moviles.interfaces.IEstadoUsuarioARepository;
import com.moviles.interfaces.IEstadoUsuarioERepository;
import com.moviles.interfaces.IRolRepository;
import com.moviles.interfaces.IUsuarioRepository;
import com.moviles.model.Asistencia;
import com.moviles.model.Usuario;
import com.moviles.services.AdminService;
import com.moviles.services.DashboardService;
import com.moviles.services.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	

	//INYECCIONES
	@Autowired
	private IAsistenciaRepository repoAsis;
	@Autowired
	private IUsuarioRepository repoUsu;
	@Autowired 
	private UsuarioService usuarioService;
	@Autowired
	private DashboardService dashboardService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private IRolRepository repoRol;
	@Autowired
	private IEstadoUsuarioERepository repoEstadoU;
	@Autowired
	private IEstadoUsuarioARepository repoEstadoAsis;
	
	
	
	@GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
    
  //VALIDACION LOGIN PARA PORTAL WEB ADMINISTRATIVO
  	@PostMapping("/login")
  	public String procesarLogin(
  	        @RequestParam String email,
  	        @RequestParam String password,
  	        HttpSession session,
  	        RedirectAttributes redirect) {

  	    LoginRequest request =
  	            new LoginRequest(
  	                    email,
  	                    password);

  	    SesionResponse respuesta =
  	            usuarioService.login(request);

  	  if(!respuesta.isSuccess()) {
          redirect.addFlashAttribute("error", respuesta.getMessage());
          return "redirect:/admin/login";
      }

      if(respuesta.getIdrol() != Roles.ADMINISTRADOR) {
          redirect.addFlashAttribute("error", "Acceso permitido solo para administradores");
          return "redirect:/admin/login";
      }

      session.setAttribute("adminLogueado", true);
      session.setAttribute("adminId", respuesta.getUserId());
      session.setAttribute("adminNombre", respuesta.getNombres());

      return "redirect:/admin/dashboard";
  	}
  	
  	@GetMapping("/dashboard")
  	public String dashboard(HttpSession session, Model model) {

  	    if(session.getAttribute("adminLogueado") == null) {
  	        return "redirect:/admin/login";
  	    }

  	    Dashboard resumen = dashboardService.obtenerResumen();
  	    model.addAttribute("dashboard", resumen);

  	    return "admin/dashboard";
  	}
  	
  	
  	@GetMapping("/asistencias")
  	public String asistencias(HttpSession session, Model model) {

  	    if(session.getAttribute("adminLogueado") == null) {
  	        return "redirect:/admin/login";
  	    }

  	    model.addAttribute("asistencias", adminService.listarAsistenciasAdmin());

  	    return "admin/asistencias";
  	}
  	
  	@GetMapping("/detalleasistencia")
  	public String detalleAsistencia(HttpSession session) {

  	    if(session.getAttribute("adminLogueado") == null) {
  	        return "redirect:/admin/login";
  	    }

  	    return "admin/detalleasistencia";
  	}
  	
  	@GetMapping("/trabajadores")
  	public String trabajadores(
  	        HttpSession session,
  	        Model model) {

  	    if(session.getAttribute("adminLogueado") == null) {
  	        return "redirect:/admin/login";
  	    }

  	    model.addAttribute(
  	            "usuarios",
  	            repoUsu.findAll());

  	    return "admin/usuarios";
  	}
  	
  	//ver detalle
  	@GetMapping("/asistencias/{id}")
  	public String detalleAsistencia(
  	        @PathVariable Integer id,
  	        HttpSession session,
  	        Model model) {

  	    if(session.getAttribute("adminLogueado") == null) {
  	        return "redirect:/admin/login";
  	    }

  	    Asistencia asistencia = repoAsis.findById(id)
  	            .orElseThrow(() -> new RuntimeException("Asistencia no encontrada"));

  	    Usuario usuario = repoUsu.findById(asistencia.getIdusuario())
  	            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

  	    model.addAttribute("asistencia", asistencia);
  	    model.addAttribute("usuario", usuario);

  	    return "admin/detalleasistencia";
  	}
  	
  	
  	//actualizar post 
  	@PostMapping("/trabajadores/actualizar")
  	public String actualizarTrabajador(
  	        @RequestParam Integer idusuario,
  	        @RequestParam String email,
  	        @RequestParam Integer idestado,
  	        @RequestParam Integer idrol,
  	        @RequestParam String direccion,
  	        @RequestParam(required = false) String pwd,
  	        RedirectAttributes redirect) {

  	    Usuario usuario = repoUsu.findById(idusuario)
  	            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

  	    // Validar que existan
  	    repoEstadoU.findById(idestado)
  	            .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

  	    repoRol.findById(idrol)
  	            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

  	    usuario.setEmail(email);
  	    usuario.setDireccion(direccion);
  	    usuario.setIdestado(idestado);
  	    usuario.setIdrol(idrol);

  	    if (pwd != null && !pwd.trim().isEmpty()) {
  	        usuario.setPwd(passwordEncoder.encode(pwd));
  	    }

  	    repoUsu.save(usuario);

  	    redirect.addFlashAttribute("success",
  	            "Trabajador actualizado correctamente");

  	    return "redirect:/admin/trabajadores";
  	}
  	
  	
  	//actualizar get
  	@GetMapping("/trabajadores/editar/{id}")
  	public String editarTrabajador(@PathVariable Integer id, Model model) {

  	    Usuario usuario = repoUsu.findById(id)
  	            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

  	    model.addAttribute("usuario", usuario);
  	    model.addAttribute("estados", repoEstadoU.findAll());
  	    model.addAttribute("roles", repoRol.findAll());

  	    return "admin/editarusuario";
  	}
  	
  	
  	//registrar get
  	@GetMapping("/trabajadores/nuevo")
  	public String nuevoTrabajador(Model model) {

  	    model.addAttribute("estados", repoEstadoU.findAll());
  	    model.addAttribute("roles", repoRol.findAll());

  	    return "admin/crearusuario";
  	}
  	
  	//registrar post
  	@PostMapping("/trabajadores/guardar")
  	public String guardarTrabajador(
  	        @RequestParam String nombres,
  	        @RequestParam String apellidos,
  	        @RequestParam String direccion,
  	        @RequestParam String email,
  	        @RequestParam String celular,
  	        @RequestParam String pwd,
  	        @RequestParam Integer idestado,
  	        @RequestParam Integer idrol,
  	        RedirectAttributes redirect) {

  	    Usuario usuario = new Usuario();

  	    usuario.setNombres(nombres);
  	    usuario.setApellidos(apellidos);
  	    usuario.setDireccion(direccion);
  	    usuario.setEmail(email);
  	    usuario.setCelular(celular);
  	    usuario.setPwd(passwordEncoder.encode(pwd));
  	    usuario.setIdestado(idestado);
  	    usuario.setIdrol(idrol);

  	    repoUsu.save(usuario);

  	    redirect.addFlashAttribute("success", "Trabajador registrado correctamente");

  	    return "redirect:/admin/trabajadores";
  	}
  	
  	//actualizar asistencia  get
  	@GetMapping("/asistencias/editar/{id}")
  	public String editarAsistencia(@PathVariable Integer id, Model model) {

  	    Asistencia asistencia = repoAsis.findById(id)
  	            .orElseThrow(() -> new RuntimeException("Asistencia no encontrada"));

  	    model.addAttribute("asistencia", asistencia);
  	    model.addAttribute("estados", repoEstadoAsis.findAll());

  	    return "admin/editarasistencia";
  	}
  	
  	//actualizar asistencia post
  	@PostMapping("/asistencias/actualizar")
  	public String actualizarAsistencia(
  	        @RequestParam Integer idasistencia,
  	        @RequestParam(required = false) LocalTime horaentrada,
  	        @RequestParam(required = false) LocalTime horasalida,
  	        @RequestParam Integer idestadoasis,
  	        @RequestParam(required = false) String observacion,
  	        RedirectAttributes redirect) {

  	    Asistencia asistencia = repoAsis.findById(idasistencia)
  	            .orElseThrow(() -> new RuntimeException("Asistencia no encontrada"));

  	    repoEstadoAsis.findById(idestadoasis)
  	            .orElseThrow(() -> new RuntimeException("Estado asistencia no encontrado"));

  	    asistencia.setHoraentrada(horaentrada);
  	    asistencia.setHorasalida(horasalida);
  	    asistencia.setIdestadoasis(idestadoasis);
  	    asistencia.setObservacion(observacion);

  	    repoAsis.save(asistencia);

  	    redirect.addFlashAttribute("success", "Asistencia actualizada correctamente");

  	    return "redirect:/admin/asistencias";
  	}
    
}