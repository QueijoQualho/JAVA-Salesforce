package com.fiap.br.services;

import java.util.List;

import com.fiap.br.models.Produto;
import com.fiap.br.repositories.ProdutoRepository;

public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository ProdutoRepository) {
        produtoRepository = ProdutoRepository;
    }

    public List<Produto> getProdutos() {
        return produtoRepository.findAll(Produto.class);
    }

    public Produto getProdutoById(int id) {
        return produtoRepository.findOne(Produto.class, id);
    }

    public void createProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    public void updateProduto(int id, Produto produto) {
        produto.setId(id);
        produtoRepository.update(produto, id);
    }

    public void deleteProduto(int id) {
        produtoRepository.delete(Produto.class, id);
    }

}
