package com.ALFREDO.ESCUELA.dto.grupos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GrupoRequest(
        @NotBlank String periodo,
        @NotNull Long cursoId,
        @NotNull Long maestroId,
        @NotNull Long aulaId) {
}
