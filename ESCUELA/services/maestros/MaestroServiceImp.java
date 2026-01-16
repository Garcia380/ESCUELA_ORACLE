package com.ALFREDO.ESCUELA.services.maestros;

import com.ALFREDO.ESCUELA.dto.maestro.MaestroRequest;
import com.ALFREDO.ESCUELA.dto.maestro.MaestroResponse;
import com.ALFREDO.ESCUELA.entities.Maestro;
import com.ALFREDO.ESCUELA.exceptions.EntidadRelacionadaException;
import com.ALFREDO.ESCUELA.mappers.MaestroMapper;
import com.ALFREDO.ESCUELA.repositories.MaestrosRepositorie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class MaestroServiceImp implements  MaestroService {

    private final MaestrosRepositorie maestrosRepositorie;

    private final MaestroMapper maestroMapper;

    @Override
    public List<MaestroResponse> listar() {
        log.info("Listado de todos los maestros solicitados");
        return maestrosRepositorie.findAll().stream()
                .map(maestroMapper::entityToResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MaestroResponse obtenerPorId(Long id) {
        return maestroMapper.entityToResponse(getMaestroOrThrow(id));
    }

    @Override
    public MaestroResponse registrar(MaestroRequest request) {
        log.info("Registrando nuevo maestro: {}", request);
        existsByEmailIgnoreCase(request.email());
        existsByTelefonoIgnoreCase(request.telefono());
        Maestro maestro = maestrosRepositorie.save(maestroMapper.requestToEntity(request));
        log.info("Nuevo maestro registrado: {}", maestro);
        return maestroMapper.entityToResponse(maestro);
    }

    @Override
    public MaestroResponse actualizar(MaestroRequest request, Long id) {
        Maestro maestro = getMaestroOrThrow(id);
        log.info("Actualizando maestro: {}", maestro);

        boolean emailCambio = !maestro.getEmail().equalsIgnoreCase(request.email());
        boolean telefonoCambio = !maestro.getTelefono().equals(request.telefono());

        if (emailCambio) {
            existsByEmailIgnoreCase(request.email());
            maestro.setEmail(request.email());
        }

        if (telefonoCambio) {
            existsByTelefonoIgnoreCase(request.telefono());
            maestro.setTelefono(request.telefono());
        }


        maestro.setNombre(request.nombre());
        maestro.setApellidoPaterno(request.apellidoPaterno());
        maestro.setApellidoMaterno(request.apellidoMaterno());

        Maestro actualizado = maestrosRepositorie.save(maestro);
        log.info("Maestro actualizado: {}", actualizado);
        return maestroMapper.entityToResponse(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        Maestro maestro = getMaestroOrThrow(id);
        log.info("Eliminando maestro con id: {}", id);
        if (maestro.getGrupos().isEmpty()) {
            maestrosRepositorie.delete(maestro);
            log.info("Maestro eliminado con id: {}", id);
        } else {
            throw new EntidadRelacionadaException("No se puede eliminar el maestro ya que tiene grupos asignados");
        }}

        private Maestro getMaestroOrThrow(Long id){
            log.info("Buscando maestro con el id: {}", id);
            return maestrosRepositorie.findById(id).orElseThrow(() ->
                    new NoSuchElementException("Maestro no encontrado con el id: " + id));
        }

        private void existsByEmailIgnoreCase(String email) {
            if (maestrosRepositorie.existsByEmailIgnoreCase(email)) {
                throw new IllegalArgumentException("Ya existe un maestro registrado con el email: " + email.toLowerCase());
            }
        }

    private void existsByTelefonoIgnoreCase(String telefono) {
        if (maestrosRepositorie.existsByEmailIgnoreCase(telefono)) {
            throw new IllegalArgumentException("Ya existe un maestro registrado con el telefono: ");
        }
    }
    }
