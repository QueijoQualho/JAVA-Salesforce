package com.fiap.br.services;

import java.util.List;

import com.fiap.br.models.ProdutoPlano;
import com.fiap.br.repositories.ProdutoPlanoRepository;
import com.fiap.br.util.validator.FieldValidator;

public class ProdutoPlanoService {
    private ProdutoPlanoRepository produtoPlanoRepository;

    public ProdutoPlanoService(ProdutoPlanoRepository produtoPlanoRepository) {
        this.produtoPlanoRepository = produtoPlanoRepository;
    }

    public List<ProdutoPlano> getProdutosPlano() {
        return produtoPlanoRepository.findAll(ProdutoPlano.class);
    }

    public ProdutoPlano getProdutoPlanoById(int id) {
        return produtoPlanoRepository.findOne(ProdutoPlano.class, id);
    }

    public boolean createProdutoPlano(ProdutoPlano produtoPlano) {

        try {
            boolean isValid = FieldValidator.requiredFieldsFilled(produtoPlano);

            if (!isValid) {
                return false;
            }

            produtoPlanoRepository.save(produtoPlano);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateProdutoPlano(int id, ProdutoPlano produtoPlano) {
        produtoPlano.setId(id);

        try {
            boolean isValid = FieldValidator.requiredFieldsFilled(produtoPlano);

            if (!isValid) {
                return false;
            }

            produtoPlanoRepository.update(produtoPlano, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteProdutoPlano(int id) {
        produtoPlanoRepository.delete(ProdutoPlano.class, id);
    }
}
