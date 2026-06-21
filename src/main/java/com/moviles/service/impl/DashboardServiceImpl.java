package com.moviles.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviles.constantes.EstadoAsistencia;
import com.moviles.dto.Dashboard;
import com.moviles.interfaces.IAsistenciaRepository;
import com.moviles.interfaces.IUsuarioRepository;
import com.moviles.services.DashboardService;

@Service
public class DashboardServiceImpl
        implements DashboardService {

    @Autowired
    private IUsuarioRepository repoUsu;

    @Autowired
    private IAsistenciaRepository repoAsis;

    @Override
    public Dashboard obtenerResumen() {

        LocalDate hoy = LocalDate.now();

        long usuarios =
                repoUsu.count();

        long asistencias =
                repoAsis.countByFecha(hoy);

        long tardanzas =
                repoAsis.countByFechaAndIdestadoasis(
                        hoy,
                        EstadoAsistencia.TARDANZA);

        long faltas =
                repoAsis.countByFechaAndIdestadoasis(
                        hoy,
                        EstadoAsistencia.FALTA);

        return new Dashboard(
                usuarios,
                asistencias,
                tardanzas,
                faltas);
    }
}