package com.ALFREDO.ESCUELA.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GRUPOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GRUPO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_CURSO", nullable = false)
    @NotNull(message = "El curso es requerido")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "ID_MAESTRO", nullable = false)
    @NotNull(message = "El maestro es requerido")
    private Maestro maestro;

    @ManyToOne
    @JoinColumn(name = "ID_AULA", nullable = false)
    @NotNull(message = "El aula es requerida")
    private Aula aula;

    @Column(nullable = false, length = 20)
    @NotBlank(message = "EL periodo es requerido")
    @Size(min = 6, max = 20, message = "El periodo debe tener entre 6 y 20 caracteres")
     private String periodo;


    //añadiendo las complementarias, para poder obetener datos de las tablas padres
    @OneToMany(mappedBy = "grupo")
    private List<Inscripcion> inscripciones = new ArrayList<>();

    @OneToMany(mappedBy = "grupo")
    private List<Horario> horarios = new ArrayList<>();


}
