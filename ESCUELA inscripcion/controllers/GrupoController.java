package com.ALFREDO.ESCUELA.controllers;

import com.ALFREDO.ESCUELA.dto.grupos.GrupoRequest;
import com.ALFREDO.ESCUELA.dto.grupos.GrupoResponse;
import com.ALFREDO.ESCUELA.services.grupos.GrupoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grupos")
public class GrupoController extends CommonController<GrupoRequest, GrupoResponse, GrupoService> {

    // Inyección por constructor (Sin @Autowired)
    public GrupoController(GrupoService service) {
        super(service);
    }
}