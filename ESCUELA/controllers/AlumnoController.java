package com.ALFREDO.ESCUELA.controllers;

import com.ALFREDO.ESCUELA.dto.alumnos.AlumnoRequest;
import com.ALFREDO.ESCUELA.dto.alumnos.AlumnoResponse;
import com.ALFREDO.ESCUELA.entities.Alumno;
import com.ALFREDO.ESCUELA.services.alumnos.AlumnoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
@RequiredArgsConstructor // Esta anotación genera el constructor para los campos 'final'
public class AlumnoController {

    private final AlumnoService service;

    @PostMapping
    public ResponseEntity<AlumnoResponse> registrar(@Valid @RequestBody AlumnoRequest request) {
        return new ResponseEntity<>(service.registrar(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlumnoResponse> actualizar(@PathVariable Long id, @Valid @RequestBody AlumnoRequest request) {
        return ResponseEntity.ok(service.actualizar(request, id));
    }

    @GetMapping
    public ResponseEntity<List<AlumnoResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

}

