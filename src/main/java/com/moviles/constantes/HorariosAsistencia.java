package com.moviles.constantes;

import java.time.LocalTime;

public final class HorariosAsistencia {

	private HorariosAsistencia() {}

    public static final LocalTime HORA_TARDANZA =
            LocalTime.of(8, 0);

    public static final LocalTime HORA_FALTA =
            LocalTime.of(12, 0);

    public static final LocalTime HORA_SALIDA_AUTOMATICA =
            LocalTime.of(17, 0);
}
