package com.moviles.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario;

    private String nombres;
    private String apellidos;
    private String direccion;
    private String email;
    private String celular;
    private String pwd;
    private String fotoperfil;
    private Integer idestado;
    private Integer idrol;
    private LocalDate fecha;

    @PrePersist
    public void prePersist() {
        fecha = LocalDate.now();
    }
    
    @ManyToOne
    @JoinColumn(name = "idestado", insertable = false, updatable = false)
    private EstadoUsuarioE objEstadoUsu;

    @ManyToOne
    @JoinColumn(name = "idrol", insertable = false, updatable = false)
    private Rol objRol;
    
}