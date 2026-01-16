package com.ALFREDO.ESCUELA.repositories;

import com.ALFREDO.ESCUELA.entities.Maestro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaestrosRepositorie extends JpaRepository<Maestro, Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByTelefono(String telefono);
}
