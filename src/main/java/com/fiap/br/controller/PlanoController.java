package com.fiap.br.controller;

import java.util.List;

import com.fiap.br.models.Plano;
import com.fiap.br.repositories.PlanoRepository;
import com.fiap.br.services.PlanoService;
import com.fiap.br.services.QueryExecutor;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("planos")
public class PlanoController {
    private final PlanoService planoService;

    public PlanoController() {
        planoService = new PlanoService(new PlanoRepository(new QueryExecutor()));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanos() {
        List<Plano> planos = planoService.findAllPlanos();
        return Response.ok(planos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanoById(@PathParam("id") int id) {
        Plano plano = planoService.findPlanoById(id);
        if (plano == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Plano não encontrado").build();
        }
        return Response.ok(plano).build();
    }

    @POST

    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPlano(Plano plano) {
        planoService.savePlano(plano);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT

    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePlano(@PathParam("id") int id, Plano plano) {
        plano.setId(id);
        planoService.updatePlano(plano, id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlano(@PathParam("id") int id) {
        planoService.deletePlano(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
