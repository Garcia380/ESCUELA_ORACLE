package com.ALFREDO.ESCUELA.entities;


import com.ALFREDO.ESCUELA.enums.DiasSemana;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "HORARIOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HORARIO")
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "El dia es requerido")
    @Enumerated(EnumType.STRING)
    private DiasSemana dia;

    @ManyToOne
    @JoinColumn(name = "ID_GRUPO", nullable = false)
    @NotNull(message = "El grupo es requerido")
    private Grupo grupo;

    @Column(name = "HORA_INICIO", nullable = false)
    @NotBlank(message = "La hora de inicio es requerida")
    @Pattern(regexp = "^[0-9]{10}$", message = "El formato de la hora es HH:mm")
    private String horaInicio;

    @Column(name = "HORA_FIN", nullable = false)
    @NotBlank(message = "La hora de Fin es requerida")
    @Pattern(regexp = "^[0-9]{10}$", message = "El formato de la hora es HH:mm")
    private String horaFin;

}
