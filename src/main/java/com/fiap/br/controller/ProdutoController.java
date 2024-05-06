package com.fiap.br.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

import com.fiap.br.models.Produto;
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
    public List<Produto> getProdutoPorId(@PathParam("id") Long id) {
        return produtoRepository.getProdutosPorPlano(id);
    }
    
}
