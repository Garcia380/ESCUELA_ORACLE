package com.ALFREDO.ESCUELA.mappers;

import com.ALFREDO.ESCUELA.dto.grupos.GrupoResponse;
import com.ALFREDO.ESCUELA.entities.Grupo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoMapper {

    public GrupoResponse toResponse(Grupo grupo) {
        String nombreCompleto = grupo.getMaestro().getNombre() + " " +
                grupo.getMaestro().getApellidoPaterno();

        // Formato: "LUNES (08:00 - 10:00)"
        List<String> horarios = grupo.getHorarios().stream()
                .map(h -> h.getDia() + " (" + h.getHoraInicio() + " - " + h.getHoraFin() + ")")
                .collect(Collectors.toList());

        return new GrupoResponse(
                grupo.getId(),
                grupo.getCurso().getNombre(),
                nombreCompleto,
                grupo.getAula().getNombre(),
                horarios,
                grupo.getPeriodo()
        );
    }
}