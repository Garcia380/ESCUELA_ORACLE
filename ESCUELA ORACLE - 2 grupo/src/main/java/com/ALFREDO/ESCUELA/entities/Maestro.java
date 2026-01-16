package com.ALFREDO.ESCUELA.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="MAESTROS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Maestro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAESTRO")
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El nombre es requerido")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @Column(name = "APELLIDO_PATERNO", nullable = false, length = 50)
    @NotBlank(message = "El Apellido paterno es requerido")
    @Size(min = 3, max = 50, message = "El apellido paterno debe tener entre 3 y 50 caracteres")
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO", nullable = false, length = 50)
    @NotBlank(message = "El Apellido materno es requerido")
    @Size(min = 3, max = 50, message = "El apellido materno debe tener entre 3 y 50 caracteres")
    private String apellidoMaterno;

    @Column(unique = true, nullable = false, length = 100)
    @NotBlank(message = "El email es requerido")
    @Size(min = 3, max = 50, message = "El email debe tener maximo caracteres")
    @Email(message = "El email debe tener un fromato válido (ejemplo@ejemplo.com)")
    private String email;

    @Column(unique = true, nullable = false, length = 10)
    @NotBlank(message = "El telefono es requerido")
    @Size(min = 10, max = 10, message = "El telefono debe tener maximo 10 caracteres")
    @Pattern(regexp = "^[0-9]{10}$", message = "El telefono debe contener unicamente 10 digitos")
    private String telefono;


    @OneToMany(mappedBy = "maestro")
    private List<Grupo> grupos = new ArrayList<>();

}
