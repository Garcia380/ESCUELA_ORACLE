package com.ALFREDO.ESCUELA.services.alumnos;

import com.ALFREDO.ESCUELA.dto.alumnos.AlumnoRequest;
import com.ALFREDO.ESCUELA.dto.alumnos.AlumnoResponse;
import com.ALFREDO.ESCUELA.entities.Alumno;
import com.ALFREDO.ESCUELA.mappers.AlumnoMapper;
import com.ALFREDO.ESCUELA.repositories.AlumnosRepositorie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor // Inyección por constructor, sin @Autowired
public class AlumnoServiceImp implements AlumnoService {

    private final AlumnosRepositorie repository;
    private final AlumnoMapper mapper;

    @Override
    public List<AlumnoResponse> listar() {
        return repository.findAll().stream()
                .map(mapper::entityToResponse)
                .toList();
    }

    @Override
    public AlumnoResponse obtenerPorId(Long id) {
        return repository.findById(id)
                .map(mapper::entityToResponse)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
    }

    @Override
    @Transactional
    public AlumnoResponse registrar(AlumnoRequest request) {
        // Validar matrícula única
        if (repository.existsByMatricula(request.matricula())) {
            throw new RuntimeException("La matrícula ya existe");
        }

        Alumno alumno = mapper.requestToEntity(request);
        alumno.setEmail(generarCorreo(request));

        // Validar email único
        if (repository.existsByEmail(alumno.getEmail())) {
            throw new RuntimeException("El correo generado ya está en uso");
        }

        alumno.setFechaIngreso(LocalDate.now()); // Fecha automática al registrar
        return mapper.entityToResponse(repository.save(alumno));
    }

    @Override
    @Transactional
    public AlumnoResponse actualizar(AlumnoRequest request, Long id) {
        Alumno alumnoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        // Validar matrícula única (si cambió)
        if (!alumnoExistente.getMatricula().equals(request.matricula()) &&
                repository.existsByMatricula(request.matricula())) {
            throw new RuntimeException("La nueva matrícula ya está en uso");
        }

        // Lógica de actualización de correo: solo si cambian nombres o apellidos
        if (!alumnoExistente.getNombre().equals(request.nombre()) ||
                !alumnoExistente.getApellidoPaterno().equals(request.apellidoPaterno()) ||
                !alumnoExistente.getApellidoMaterno().equals(request.apellidoMaterno())) {
            alumnoExistente.setEmail(generarCorreo(request));
        }

        alumnoExistente.setNombre(request.nombre());
        alumnoExistente.setApellidoPaterno(request.apellidoPaterno());
        alumnoExistente.setApellidoMaterno(request.apellidoMaterno());
        alumnoExistente.setMatricula(request.matricula());

        return mapper.entityToResponse(repository.save(alumnoExistente));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Alumno no encontrado");
        }
        repository.deleteById(id);
    }

    private String generarCorreo(AlumnoRequest req) {
        return (req.nombre().charAt(0) + req.apellidoPaterno() + req.apellidoMaterno().charAt(0))
                .toLowerCase().replace(" ", "") + "@escuela.com";
    }
}