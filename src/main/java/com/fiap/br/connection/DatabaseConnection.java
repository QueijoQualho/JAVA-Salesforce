package com.fiap.br.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection connection;
    public static String URL_ORACLE;
    public static String USER;
    public static String PASSWORD;

    static {
        try (InputStream inputStream = DatabaseConnection.class.getClassLoader()
                .getResourceAsStream("database.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            URL_ORACLE = properties.getProperty("jdbc.url");
            USER = properties.getProperty("jdbc.username");
            PASSWORD = properties.getProperty("jdbc.password");
            System.out.println(URL_ORACLE);
            System.out.println(USER);
            System.out.println(PASSWORD);
        } catch (IOException e) {
            throw new RuntimeException("Falha ao carregar as propriedades do banco de dados", e);
        }
    }

    private DatabaseConnection() {
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed() || !connection.isValid(5)) {
                createConnection();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL_ORACLE, USER, PASSWORD);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao conectar ao banco de dados.", ex);
        }
    }

}
