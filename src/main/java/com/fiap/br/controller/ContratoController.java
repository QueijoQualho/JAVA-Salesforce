package com.fiap.br.controller;

import java.util.List;

import com.fiap.br.models.Contrato;
import com.fiap.br.repositories.ContratoRepository;
import com.fiap.br.services.ContratoService;
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

@Path("contratos")
public class ContratoController {
    private final ContratoService contratoService;

    public ContratoController() {
        contratoService = new ContratoService(new ContratoRepository(new QueryExecutor()));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContratos() {
        List<Contrato> contratos = contratoService.getContratos();
        return Response.ok(contratos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContratoById(@PathParam("id") int id) {
        Contrato contrato = contratoService.getContratoById(id);
        if (contrato == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Contrato não encontrado").build();
        }
        return Response.ok(contrato).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createContrato(Contrato contrato) {
        boolean isValid = contratoService.createContrato(contrato);
        if (!isValid) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateContrato(@PathParam("id") int id, Contrato contrato) {
        boolean isValid = contratoService.updateContrato(contrato, id);
        if (!isValid) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteContrato(@PathParam("id") int id) {
        contratoService.deleteContrato(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
