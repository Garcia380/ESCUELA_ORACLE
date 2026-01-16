package com.ALFREDO.ESCUELA.mappers;

import com.ALFREDO.ESCUELA.dto.alumnos.AlumnoResponse;
import com.ALFREDO.ESCUELA.dto.alumnos.AlumnoRequest;
import com.ALFREDO.ESCUELA.entities.Alumno;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

@Component
public class AlumnoMapper implements CommonMapper<AlumnoRequest, AlumnoResponse, Alumno> {

    @Override
    public AlumnoResponse entityToResponse(Alumno entity) {
        // Mapear la lista de calificaciones desde las inscripciones
        List<AlumnoResponse.CalificacionResumen> calificaciones = (entity.getInscripciones() == null)
                ? Collections.emptyList()
                : entity.getInscripciones().stream()
                .filter(i -> i.getCalificacion() != null)
                .map(i -> new AlumnoResponse.CalificacionResumen(
                        i.getGrupo().getCurso().getNombre(),
                        i.getGrupo().getPeriodo(),
                        i.getCalificacion().getCalificacion()
                ))
                .toList();

        BigDecimal promedio = calcularPromedio(calificaciones);

        return new AlumnoResponse(
                entity.getId(),
                entity.getNombre() + " " +
                        entity.getApellidoPaterno() + " " +
                        entity.getApellidoMaterno(),
                entity.getEmail(),
                entity.getMatricula(),
                calificaciones, promedio
        );
    }

    @Override
    public Alumno requestToEntity(AlumnoRequest request) {
        Alumno entity = new Alumno();
        entity.setNombre(request.nombre());
        entity.setApellidoPaterno(request.apellidoPaterno());
        entity.setApellidoMaterno(request.apellidoMaterno());
        entity.setMatricula(request.matricula());
        return entity;
    }

    private BigDecimal calcularPromedio(List<AlumnoResponse.CalificacionResumen> calificaciones) {
        if (calificaciones == null || calificaciones.isEmpty()) {
            return BigDecimal.ZERO.setScale(1, RoundingMode.HALF_UP);
        }

        BigDecimal suma = calificaciones.stream()
                .map(AlumnoResponse.CalificacionResumen::calificacion)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        return suma.divide(BigDecimal.valueOf(calificaciones.size()), 1, RoundingMode.HALF_UP);
    }



}




















