package com.fiap.br.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fiap.br.util.connection.DatabaseConnection;

public class QuerySelector {
    public static ResultSet executeQuery(String sql, Object... params) {
        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao executar a consulta SQL.", e);
        }
    }
}
