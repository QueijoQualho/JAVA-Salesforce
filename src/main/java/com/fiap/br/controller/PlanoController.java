package com.fiap.br.controller;

import com.fiap.br.models.Plano;
import com.fiap.br.repositories.PlanoRepository;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("planos")
public class PlanoController {
    private final PlanoRepository planoRepository;

    public PlanoController() {
        planoRepository = new PlanoRepository();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanos() {
        List<Plano> planos = planoRepository.getPlano();
        return Response.ok(planos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanoById(@PathParam("id") Long id) {
        Plano plano = planoRepository.getPlanoById(id);
        if (plano != null) {
            return Response.ok(plano).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlano(Plano plano) {
        planoRepository.savePlano(plano);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePlano(@PathParam("id") Long id, Plano plano) {
        plano.setId(id);
        planoRepository.updatePlano(plano);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePlano(@PathParam("id") Long id) {
        planoRepository.deletePlano(id);
        return Response.noContent().build();
    }
}
