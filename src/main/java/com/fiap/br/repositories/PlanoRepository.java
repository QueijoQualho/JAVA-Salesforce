package com.fiap.br.repositories;

import com.fiap.br.models.Plano;
import com.fiap.br.services.QueryExecutor;

public class PlanoRepository extends Repository<Plano> {

    public PlanoRepository(QueryExecutor queryExecutor) {
        super(queryExecutor);
    }

}
