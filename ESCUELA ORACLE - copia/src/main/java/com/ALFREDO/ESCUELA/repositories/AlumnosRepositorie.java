package com.ALFREDO.ESCUELA.repositories;

import com.ALFREDO.ESCUELA.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnosRepositorie extends JpaRepository<Alumno, Long> {

}
