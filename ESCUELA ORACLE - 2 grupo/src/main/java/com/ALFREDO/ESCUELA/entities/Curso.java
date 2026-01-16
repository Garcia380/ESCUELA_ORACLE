package com.ALFREDO.ESCUELA.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CURSOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CURSO")
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El nombre es requerido")
    @Size(min = 5, max = 100, message = "El nombre debe tener entre 5 y 100 caracteres")
    private String nombre;

    @Column(length = 50)
    @NotBlank(message = "La descripcion es requerida")
    @Size(max = 200, message = "La descripcion debe contener maximo 200 caracteres")
    private String descripcion;

    @Column(nullable = false)
    @NotNull(message = "Los creditos son requeridos")
    @Min(value = 1, message = "Los creditos minimos son 1")
    @Max(value = 10, message = "Los creditos maximos son 10")
    private Integer creditos;

    @OneToMany(mappedBy = "curso")
    private List<Grupo> grupos = new ArrayList<>();


}
