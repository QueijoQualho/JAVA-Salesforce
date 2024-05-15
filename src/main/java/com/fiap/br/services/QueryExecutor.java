package com.fiap.br.services;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.br.util.connection.DatabaseConnection;

public class QueryExecutor {

    private Connection connection = DatabaseConnection.getConnection();

    public <T> List<T> execute(Class<T> entityClass, String sql, Object[] params, CRUDOperation operation,
            Optional<Integer> id) {
        List<T> results = new ArrayList<>();

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {

            if (id.isPresent()) {
                Integer idValue = id.get();

                params = (params == null) ? new Object[] { idValue } : Arrays.copyOf(params, params.length + 1);
                params[params.length - 1] = idValue;

            }

            if (params != null) {
                setParameters(pstm, params);
            }

            switch (operation) {
                case CREATE:
                case UPDATE:
                case DELETE:
                    int affectedRows = pstm.executeUpdate();
                    System.out.println("Rows affected: " + affectedRows);
                    break;
                case READ:
                    try (ResultSet rs = pstm.executeQuery()) {
                        while (rs.next()) {
                            T entity = mapResultSetToEntity(rs, entityClass);
                            results.add(entity);
                        }
                    }
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    private void setParameters(PreparedStatement statement, Object[] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }

    }

    private <T> T mapResultSetToEntity(ResultSet rs, Class<T> entityClass) throws SQLException {
        T entity = null;
        try {
            entity = entityClass.getDeclaredConstructor().newInstance();
            Map<String, String> columnNames = getColumnNames(entityClass);

            for (Map.Entry<String, String> entry : columnNames.entrySet()) {
                String fieldName = entry.getKey();
                String columnName = entry.getValue();
                Field field = entityClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(entity, rs.getObject(columnName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    public <T> Map<String, String> getColumnNames(Class<T> entityClass) {
        Map<String, String> columnNames = new LinkedHashMap<>();
        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonProperty.class)) {
                JsonProperty annotation = field.getAnnotation(JsonProperty.class);
                columnNames.put(field.getName(), annotation.value());
            }
        }
        return columnNames;
    }

}
