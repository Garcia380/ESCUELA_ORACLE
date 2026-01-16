package com.ALFREDO.ESCUELA.dto.alumnos;

import java.math.BigDecimal;
import java.util.List;

public record AlumnoResponse(
        Long id,
        String nombre,
        String matricula,
        String email,
        List<CalificacionResumen> calificaciones,
        BigDecimal promedio

) {
    public record CalificacionResumen(
            String nombreCurso,
            String periodo,
            BigDecimal calificacion
    ) {}
}
