package com.fiap.br.repositories;

import java.util.List;
import java.util.Optional;

import com.fiap.br.models.Usuario;
import com.fiap.br.models.enums.CRUDOperation;
import com.fiap.br.services.QueryExecutor;

public class UsuarioRepository extends Repository<Usuario> {

    public UsuarioRepository(QueryExecutor queryExecutor) {
        super(queryExecutor);
    }

    public Usuario findByEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";

        try {
            List<Usuario> result = queryExecutor.execute(Usuario.class, sql, new Object[] { email }, CRUDOperation.READ,
                    Optional.empty());
            if (!result.isEmpty()) {
                return result.get(0);
            }
        } catch (Exception e) {
            logError("Erro ao encontrar usuário por e-mail: " + e.getMessage());
        }
        return null;
    }
}
