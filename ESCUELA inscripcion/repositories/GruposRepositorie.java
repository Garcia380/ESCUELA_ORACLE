package com.ALFREDO.ESCUELA.repositories;

import com.ALFREDO.ESCUELA.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GruposRepositorie extends JpaRepository<Grupo, Long> {

    boolean existsByPeriodo(String periodo);
}
