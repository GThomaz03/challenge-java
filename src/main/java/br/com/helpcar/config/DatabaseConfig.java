package br.com.helpcar.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private String url, user, password;

    public DatabaseConfig() {
        this.url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
        this.user = "rm558637";
        this.password = "010405";

        // Carrega o driver JDBC do Oracle
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Driver JDBC do Oracle não encontrado.", e);
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }
}
