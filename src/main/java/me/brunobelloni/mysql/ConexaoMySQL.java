package me.brunobelloni.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

    public static String status = "Não conectou...";

    public ConexaoMySQL() {
        openConnection();
    }

    public static java.sql.Connection openConnection() {
        Connection connection = null;

        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);

            String password = "password";
            String username = "user";
            String mydatabase = "mine";
            String serverName = "6b0fbd4aed1b";
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

            System.out.println("teste!!");
            DriverManager.setLoginTimeout(1000);

            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                status = ("STATUS--->Conectado com sucesso!");
            } else {
                status = ("STATUS--->Não foi possivel realizar conexão");
            }
            return connection;

        } catch (ClassNotFoundException e) {
            System.out.println("O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            System.out.println(e);
            return null;
        }
    }

    public static String statusConection() {
        return status;
    }

    public static void closeConnection() throws SQLException {
        openConnection().close();
    }

    public static java.sql.Connection resetConnection() throws SQLException {
        closeConnection();
        return openConnection();
    }
}
