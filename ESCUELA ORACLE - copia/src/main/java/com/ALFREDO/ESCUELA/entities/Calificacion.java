package com.ALFREDO.ESCUELA.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="CALIFICACIONES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CALIFICACION")
    private Long id;

    @OneToOne
    @JoinColumn (name = "ID_INSCRIPCION", nullable = false, unique = true)
    @NotNull(message = "La inscripcion es requerida")
    private Inscripcion inscripcion;

   @Column(nullable = false)
   @DecimalMin(value = "0.0", message = "El valor minimo de la calificacion es 0.0")
   @DecimalMax(value = "10.0", message = "El valor maximo de la calificacion es 10.0")
   private BigDecimal calificacion;

   @Column(name = "FECHA_REGISTRO", nullable = false)
   @NotNull(message = "La fecha de registro es requerida")
   @PastOrPresent(message = "La fecha de ingreso no puede ser futura")
   private LocalDate fechaRegistro;

}
