package com.fiap.br.repositories;

import com.fiap.br.models.ProdutoPlano;
import com.fiap.br.services.QueryExecutor;

public class ProdutoPlanoRepository extends Repository<ProdutoPlano>{
    public ProdutoPlanoRepository(QueryExecutor queryExecutor) {
        super(queryExecutor);
    }
}
