package com.fiap.br.repositories;

import com.fiap.br.models.Produto;
import com.fiap.br.services.QueryExecutor;

public class ProdutoRepository extends Repository<Produto> {
    
    public ProdutoRepository(QueryExecutor queryExecutor) {
        super(queryExecutor);
    }
        
}
