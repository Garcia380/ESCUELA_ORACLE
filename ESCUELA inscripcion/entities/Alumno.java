package com.ALFREDO.ESCUELA.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ALUMNOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ALUMNO")
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El nombre es requerido")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @Column(name = "APELLIDO_PATERNO")
    @NotBlank(message = "El Apellido Paterno es requerido")
    @Size(min = 3, max = 50, message = "El apellido Paterno debe tener entre 3 y 50 caracteres")
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO")
    @NotBlank(message = "El Apellido Materno es requerido")
    @Size(min = 3, max = 50, message = "El apellido Materno debe tener entre 3 y 50 caracteres")
    private String apellidoMaterno;

    @Column(unique = true, nullable = false, length = 100)
    @NotBlank(message = "El email es requerido")
    @Size(min = 3, max = 50, message = "El email debe tener maximo caracteres")
    @Email(message = "El email debe tener un fromato válido (ejemplo@ejemplo.com)")
    private String email;

    @Column(name = "MATRICULA")
    @NotBlank(message = "La matricula es requerida")
    @Size(min = 10, max = 10, message = "La matricula debe conetner 10 caracteres")
    private String matricula;

    @Column(name = "FECHA_INGRESO", updatable = false)
    @NotNull(message = "La fecha de ingreso es requerida")
    @PastOrPresent(message = "La fecha de ingreso no puede ser futura")
    private LocalDate fechaIngreso;

    @OneToMany(mappedBy = "alumno")
    private List<Inscripcion> inscripciones = new ArrayList<>();

}
