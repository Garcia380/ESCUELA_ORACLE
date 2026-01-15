package com.ALFREDO.ESCUELA.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AULAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AULA")
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    @NotBlank(message = "El nombre es requerido")
    @Size(min = 5, max = 30, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @Column(nullable = false)
    @NotNull(message = "La capacidad es requerida")
    @Min(value = 10, message = "La capacidad minima son 10")
    @Max(value = 50, message = "La capacidad maxima son 50")
    private Integer capacidad;

    @OneToMany(mappedBy = "aula")
    private List<Grupo> grupos = new ArrayList<>();
}
