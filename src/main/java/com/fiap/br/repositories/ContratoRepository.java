package com.fiap.br.repositories;

import com.fiap.br.models.Contrato;
import com.fiap.br.services.QueryExecutor;

public class ContratoRepository extends Repository<Contrato>{
    public ContratoRepository(QueryExecutor queryExecutor) {
        super(queryExecutor);
    }
}
