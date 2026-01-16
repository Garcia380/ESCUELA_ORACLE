package com.ALFREDO.ESCUELA.dto.grupos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record GrupoResponse(
        Long id,
        String nombreCurso,
        String nombreMaestro,
        String nombreAula,
        List<String> horarios,
        String periodo
) {}
