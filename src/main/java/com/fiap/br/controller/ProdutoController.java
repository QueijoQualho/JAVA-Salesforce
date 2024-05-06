package com.fiap.br.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fiap.br.models.Produto.Produto;
import com.fiap.br.repositories.ProdutoRepository;

@Path("produtos")
public class ProdutoController {
    private final ProdutoRepository produtoRepository;

    public ProdutoController() {
        produtoRepository = new ProdutoRepository();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produto> getProdutos() {
        return produtoRepository.getAllProdutos();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produto> getProduto(@PathParam("id") int id) {
        return produtoRepository.getProdutosPorPlano((long) id);
    }

}
