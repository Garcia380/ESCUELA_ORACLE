package com.ALFREDO.ESCUELA.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.NoSuchElementException;

@AllArgsConstructor
@Getter
public enum DiasSemana {
    LUNES("Lunes"),
    MARTES("Martes"),
    MIERCOLES("Miercoles"),
    JUEVES("Jueves"),
    VIERNES("Viernes"),
    SABADO("Sabado"),
    DOMINGO("Domingo");

    private final String descripcion;

    private static String quitarAcentos (String s) {
        return s.toLowerCase()
                .replace("á", "a").replace("é", "e")
                .replace("í", "i").replace("ó", "o")
                .replace("ú", "u").replace("ü", "u");
    }

    public static DiasSemana fromDescripcion(String descripcion) {
        String buscado = quitarAcentos(descripcion.trim());
        for (DiasSemana dia : values()) {
            String descDia = quitarAcentos(dia.descripcion);
            if (descDia.equals(buscado))
                return dia;
        }
        throw new NoSuchElementException("No existe Dia de la semana con la descripcion: " + descripcion);
    }
}
