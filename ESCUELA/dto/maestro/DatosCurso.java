package com.ALFREDO.ESCUELA.dto.maestro;

import java.util.List;

public record DatosCurso (
        String nombre,
        String aula,
        List<String> horarios,
        String periodo
        ) {

}
