package com.fiap.br.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import com.fiap.br.models.Produto;
import com.fiap.br.repositories.ProdutoRepository;
import com.fiap.br.services.QueryExecutor;

@Path("produtos")
public class ProdutoController {
    private final ProdutoRepository produtoRepository;

    public ProdutoController() {
        produtoRepository = new ProdutoRepository(new QueryExecutor());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produto> getProdutos() {
        return produtoRepository.findAll(Produto.class);
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produto> getProdutosbyId(@PathParam("id") int id) {
            return produtoRepository.findOne(Produto.class, id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduto(Produto produto) {
        produtoRepository.save(produto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduto(@PathParam("id") int id, Produto produto) {
        produto.setId(id); 
        produtoRepository.update(produto, id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduto(@PathParam("id") int id) {
        produtoRepository.delete(Produto.class, id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
