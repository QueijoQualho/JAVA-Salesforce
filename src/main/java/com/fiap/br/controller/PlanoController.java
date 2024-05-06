package com.fiap.br.controller;

import com.fiap.br.repositories.PlanoRepository;

import jakarta.ws.rs.Path;

@Path("planos")
public class PlanoController {
    private final PlanoRepository planoRepository;

    PlanoController() {
        planoRepository = new PlanoRepository();
    }

    
}
