package com.ALFREDO.ESCUELA.services.grupos;

import com.ALFREDO.ESCUELA.dto.grupos.GrupoRequest;
import com.ALFREDO.ESCUELA.dto.grupos.GrupoResponse;
import com.ALFREDO.ESCUELA.entities.*;
import com.ALFREDO.ESCUELA.repositories.*;
import com.ALFREDO.ESCUELA.mappers.GrupoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrupoServiceImp implements GrupoService {

    private final GruposRepositorie gruposRepositorie;
    private final CursosRepositorie cursosRepositorie;
    private final MaestrosRepositorie maestrosRepositorie;
    private final AulasRepositorie aulasRepositorie;
    private final GrupoMapper grupoMapper;

    public GrupoServiceImp(GruposRepositorie gruposRepositorie,
                           CursosRepositorie cursosRepositorie,
                           MaestrosRepositorie maestrosRepositorie,
                           AulasRepositorie aulasRepositorie,
                           GrupoMapper grupoMapper) {
        this.gruposRepositorie = gruposRepositorie;
        this.cursosRepositorie = cursosRepositorie;
        this.maestrosRepositorie = maestrosRepositorie;
        this.aulasRepositorie = aulasRepositorie;
        this.grupoMapper = grupoMapper;
    }

    @Override
    @Transactional
    public GrupoResponse registrar(GrupoRequest request) {
        // Validar periodo único
        if (gruposRepositorie.existsByPeriodo(request.periodo())) {
            throw new RuntimeException("El periodo '" + request.periodo() + "' ya existe.");
        }

        // Validar existencia de IDs
        Curso curso = cursosRepositorie.findById(request.cursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado con ID: " + request.cursoId()));

        Maestro maestro = maestrosRepositorie.findById(request.maestroId())
                .orElseThrow(() -> new EntityNotFoundException("Maestro no encontrado con ID: " + request.maestroId()));

        Aula aula = aulasRepositorie.findById(request.aulaId())
                .orElseThrow(() -> new EntityNotFoundException("Aula no encontrada con ID: " + request.aulaId()));

        Grupo grupo = new Grupo();
        grupo.setPeriodo(request.periodo());
        grupo.setCurso(curso);
        grupo.setMaestro(maestro);
        grupo.setAula(aula);

        return grupoMapper.toResponse(gruposRepositorie.save(grupo));
    }

    @Override
    @Transactional(readOnly = true)
    public List<GrupoResponse> listar() {
        return gruposRepositorie.findAll().stream()
                .map(grupoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public GrupoResponse obtenerPorId(Long id) {
        return gruposRepositorie.findById(id)
                .map(grupoMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Grupo no encontrado"));
    }

    @Override
    @Transactional
    public GrupoResponse actualizar(GrupoRequest request, Long id) {
        Grupo grupo = gruposRepositorie.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grupo no encontrado"));

        // Validar que si cambia el periodo, el nuevo no exista
        if (!grupo.getPeriodo().equals(request.periodo()) && gruposRepositorie.existsByPeriodo(request.periodo())) {
            throw new RuntimeException("El nuevo periodo ya existe.");
        }

        grupo.setPeriodo(request.periodo());
        grupo.setCurso(cursosRepositorie.getReferenceById(request.cursoId()));
        grupo.setMaestro(maestrosRepositorie.getReferenceById(request.maestroId()));
        grupo.setAula(aulasRepositorie.getReferenceById(request.aulaId()));

        return grupoMapper.toResponse(gruposRepositorie.save(grupo));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!gruposRepositorie.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar: Grupo no encontrado");
        }
        gruposRepositorie.deleteById(id);
    }
}