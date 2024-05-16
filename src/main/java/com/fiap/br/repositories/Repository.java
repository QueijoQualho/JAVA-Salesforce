package com.fiap.br.repositories;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.br.models.enums.CRUDOperation;
import com.fiap.br.services.QueryExecutor;
import com.fiap.br.util.annotations.TableName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Repository<T> {
    private static final Logger logger = LogManager.getLogger(Repository.class);

    private QueryExecutor queryExecutor;

    public Repository(QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    public T findOne(Class<T> entityClass, int id) {
        String tableName = getTableName(entityClass);
        Optional<Integer> idOptional = Optional.of(id);

        try {
            Field idField = entityClass.getDeclaredField("id");
            idField.setAccessible(true);

            JsonProperty annotation = idField.getAnnotation(JsonProperty.class);

            String sql = "SELECT * FROM " + tableName + " WHERE " + annotation.value() + " = ?";
            List<T> result = queryExecutor.execute(entityClass, sql, null, CRUDOperation.READ, idOptional);

            if (!result.isEmpty()) {
                return result.get(0);
            }
        } catch (Exception e) {
            logger.error("Erro ao pegar " + tableName + ": " + e.getMessage(), e);
        }
        return null;
    }

    public List<T> findAll(Class<T> entityClass) {
        String tableName = getTableName(entityClass);
        String sql = "SELECT * FROM " + tableName;
        return queryExecutor.execute(entityClass, sql, null, CRUDOperation.READ, Optional.empty());
    }

    public void save(T entity) {
        Class<?> entityClass = entity.getClass();
        String tableName = getTableName(entityClass);
        Map<String, String> columnNames = queryExecutor.getColumnNames(entityClass);
        StringBuilder sqlQuery = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
        StringBuilder valuesBuilder = new StringBuilder(" VALUES (");

        try {
            List<Object> params = buildParamsList(entity);

            columnNames.remove("id");

            if (params.size() > 0) {
                params.remove(0);
            }

            for (String columnName : columnNames.values()) {
                sqlQuery.append(columnName).append(", ");
                valuesBuilder.append("?, ");
            }

            sqlQuery.deleteCharAt(sqlQuery.length() - 2).append(")");
            valuesBuilder.deleteCharAt(valuesBuilder.length() - 2).append(")");
            sqlQuery.append(valuesBuilder);

            queryExecutor.execute(entityClass, sqlQuery.toString(), params.toArray(), CRUDOperation.CREATE,
                    Optional.empty());
        } catch (Exception e) {
            logger.error("Error occurred while saving entity: " + e.getMessage(), e);
        }
    }

    public void update(T entity, int id) {
        Class<?> entityClass = entity.getClass();
        String tableName = getTableName(entityClass);
        Map<String, String> columnNames = queryExecutor.getColumnNames(entityClass);
        StringBuilder sqlQuery = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
        Optional<Integer> idOptional = Optional.of(id);

        try {
            List<Object> params = buildParamsList(entity);

            for (String columnName : columnNames.values()) {
                sqlQuery.append(columnName).append(" = ?, ");
            }

            sqlQuery.deleteCharAt(sqlQuery.length() - 2);

            sqlQuery.append(" WHERE " + columnNames.get("id") + "= ?");

            queryExecutor.execute(entityClass, sqlQuery.toString(), params.toArray(), CRUDOperation.UPDATE, idOptional);
        } catch (Exception e) {
            logger.error("Error occurred while updating entity: " + e.getMessage(), e);
        }
    }

    public void delete(Class<T> entityClass, int id) {
        String tableName = getTableName(entityClass);
        Optional<Integer> idOptional = Optional.of(id);

        try {
            Field idField = entityClass.getDeclaredField("id");
            idField.setAccessible(true);

            JsonProperty annotation = idField.getAnnotation(JsonProperty.class);

            StringBuilder sqlQuery = new StringBuilder("DELETE FROM ").append(tableName)
                    .append(" WHERE " + annotation.value() + "= ?");

            queryExecutor.execute(entityClass, sqlQuery.toString(), null, CRUDOperation.DELETE, idOptional);
        } catch (Exception e) {
            logger.error("Error occurred while deleting entity: " + e.getMessage(), e);
        }
    }

    private List<Object> buildParamsList(T entity) throws IllegalAccessException {
        List<Object> params = new ArrayList<>();
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonProperty.class)) {
                Object value = field.get(entity);
                params.add(value);
            }
        }
        return params;
    }

    private String getTableName(Class<?> entityClass) {
        if (entityClass.isAnnotationPresent(TableName.class)) {
            TableName tableNameAnnotation = entityClass.getAnnotation(TableName.class);
            return tableNameAnnotation.value();
        } else {
            return entityClass.getSimpleName().toLowerCase();
        }
    }
}
