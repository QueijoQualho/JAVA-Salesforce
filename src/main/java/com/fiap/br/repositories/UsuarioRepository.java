package com.fiap.br.repositories;

import java.sql.Connection;
import java.util.List;

import com.fiap.br.connection.DatabaseConnection;
import com.fiap.br.models.pessoa.Usuario;


public abstract class UsuarioRepository {
    protected Connection connection = DatabaseConnection.getConnection();

    public abstract List<Usuario> getUsuarios();

    public abstract Usuario getUsuarioById(Long id);

    public abstract void saveUsuario(Usuario usuario);

    public abstract void updateUsuario(Usuario usuario);

    public abstract void deleteUsuario(Long id);
}
