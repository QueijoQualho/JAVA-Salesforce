package com.fiap.br.controller;

import java.util.List;

import com.fiap.br.models.Produto;
import com.fiap.br.repositories.ProdutoRepository;
import com.fiap.br.services.ProdutoService;
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

@Path("produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController() {
        produtoService = new ProdutoService(new ProdutoRepository(new QueryExecutor()));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProdutos() {
        try {
            List<Produto> produtos = produtoService.getProdutos();
            return Response.ok(produtos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao obter produtos: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProdutosbyId(@PathParam("id") int id) {
        try {
            Produto produto = produtoService.getProdutoById(id);
            if (produto != null) {
                return Response.ok(produto).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Produto não encontrado").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao obter produto: " + e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduto(Produto produto) {
        try {
            produtoService.createProduto(produto);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao criar produto: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduto(@PathParam("id") int id, Produto produto) {
        try {
            produto.setId(id);
            produtoService.updateProduto(id, produto);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao atualizar produto: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduto(@PathParam("id") int id) {
        try {
            produtoService.deleteProduto(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao deletar produto: " + e.getMessage()).build();
        }
    }
}
