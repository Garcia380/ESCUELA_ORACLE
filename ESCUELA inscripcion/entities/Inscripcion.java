package com.ALFREDO.ESCUELA.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="INSCRIPCIONES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INSCRIPCION")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_ALUMNO", nullable = false)
    @NotNull(message = "El alumno es requerido")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "ID_GRUPO", nullable = false)
    @NotNull(message = "El grupo es requerido")
    private Grupo grupo;

    @Column(name = "FECHA_INSCRIPCION", insertable = false, updatable = false)
    @NotNull(message = "la fecha de ingreso es requerida")
    @PastOrPresent(message = "La fecha de inscripcion no puede ser futura")
    private LocalDate fechaInscripcion;

    @OneToOne(mappedBy = "inscripcion")
    private Calificacion calificacion;


}
