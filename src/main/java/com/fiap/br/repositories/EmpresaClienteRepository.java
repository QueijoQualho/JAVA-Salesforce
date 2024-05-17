package com.fiap.br.repositories;

import com.fiap.br.models.EmpresaCliente;
import com.fiap.br.services.QueryExecutor;

public class EmpresaClienteRepository extends Repository<EmpresaCliente>{

    public EmpresaClienteRepository(QueryExecutor queryExecutor) {
        super(queryExecutor);
    }
}
