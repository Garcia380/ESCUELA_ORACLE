package com.ALFREDO.ESCUELA.controllers;

import com.ALFREDO.ESCUELA.dto.maestro.MaestroRequest;
import com.ALFREDO.ESCUELA.dto.maestro.MaestroResponse;
import com.ALFREDO.ESCUELA.services.maestros.MaestroService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/maestros")

public class MaestroController extends CommonController<MaestroRequest, MaestroResponse, MaestroService> {

    public MaestroController(MaestroService service) {
        super(service);
    }
}
