package com.fiap.br.controller;

import java.util.List;

import com.fiap.br.models.ProdutoPlano;
import com.fiap.br.repositories.ProdutoPlanoRepository;
import com.fiap.br.services.ProdutoPlanoService;
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

@Path("produtoPlano")
public class ProdutoPlanoController {
    private final ProdutoPlanoService produtoPlanoService;

    public ProdutoPlanoController() {
        produtoPlanoService = new ProdutoPlanoService(new ProdutoPlanoRepository(new QueryExecutor()));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanos() {
        List<ProdutoPlano> planos = produtoPlanoService.getProdutosPlano();
        return Response.ok(planos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanoById(@PathParam("id") int id) {
        ProdutoPlano plano = produtoPlanoService.getProdutoPlanoById(id);
        if (plano == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Plano não encontrado").build();
        }
        return Response.ok(plano).build();
    }

    @POST

    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPlano(ProdutoPlano produtoPlano) {
        boolean isValid = produtoPlanoService.createProdutoPlano(produtoPlano);
        if (!isValid) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT

    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePlano(@PathParam("id") int id, ProdutoPlano produtoPlano) {
        boolean isValid = produtoPlanoService.updateProdutoPlano(id, produtoPlano);
        if (!isValid) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlano(@PathParam("id") int id) {
        produtoPlanoService.deleteProdutoPlano(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
