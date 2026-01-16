package com.ALFREDO.ESCUELA.dto.maestro;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record MaestroRequest(


    @NotBlank(message = "El nombre es requerido")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    String nombre,

    @NotBlank(message = "El Apellido paterno es requerido")
    @Size(min = 3, max = 50, message = "El apellido paterno debe tener entre 3 y 50 caracteres")
    String apellidoPaterno,

    @NotBlank(message = "El Apellido materno es requerido")
    @Size(min = 3, max = 50, message = "El apellido materno debe tener entre 3 y 50 caracteres")
    String apellidoMaterno,

    @Size(min = 3, max = 50, message = "El email debe tener maximo 50 caracteres")
    @Email(message = "El email debe tener un fromato válido (ejemplo@ejemplo.com)")
    String email,

    @NotBlank(message = "El telefono es requerido")
    @Size(min = 10, max = 10, message = "El telefono debe tener maximo 10 caracteres")
    @Pattern(regexp = "^[0-9]{10}$", message = "El telefono debe contener unicamente 10 digitos")
    String telefono

) {
}
