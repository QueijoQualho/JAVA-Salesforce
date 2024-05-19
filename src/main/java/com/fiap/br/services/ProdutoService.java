package com.fiap.br.services;

import java.util.List;

import com.fiap.br.models.Produto;
import com.fiap.br.repositories.ProdutoRepository;
import com.fiap.br.util.validator.FieldValidator;

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

    public boolean createProduto(Produto produto) {
        try {
            boolean isValid = FieldValidator.requiredFieldsFilled(produto);

            if (!isValid) {
                return false;
            }

            produtoRepository.save(produto);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean updateProduto(int id, Produto produto) {

        try {
            boolean isValid = FieldValidator.requiredFieldsFilled(produto);

            if (!isValid) {
                return false;
            }

            produtoRepository.update(produto, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteProduto(int id) {
        produtoRepository.delete(Produto.class, id);
    }

}
