package com.moviles.scheduler;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.moviles.constantes.HorariosAsistencia;
import com.moviles.interfaces.IAsistenciaRepository;
import com.moviles.model.Asistencia;

@Component
public class AsistenciaScheduler {

    @Autowired
    private IAsistenciaRepository repoAsis;

    @Scheduled(cron = "0 59 23 * * *")
    public void cerrarAsistenciasPendientes() {

        List<Asistencia> pendientes =
                repoAsis.findByFechaAndHorasalidaIsNull(
                        LocalDate.now());

        for(Asistencia asistencia : pendientes) {

            if(asistencia.getHoraentrada() == null) {
                continue;
            }

            asistencia.setHorasalida(
                    HorariosAsistencia.HORA_SALIDA_AUTOMATICA);

            Duration duracion =
                    Duration.between(
                            asistencia.getHoraentrada(),
                            HorariosAsistencia.HORA_SALIDA_AUTOMATICA);

            double horasTrabajadas =
                    duracion.toMinutes() / 60.0;

            asistencia.setHorastrabajadas(
                    Math.round(
                            horasTrabajadas * 100.0) / 100.0);

            repoAsis.save(asistencia);
        }
    }
}