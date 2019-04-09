package me.brunobelloni.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoMySQL {

    private static final String USUARIO = "root";
    private static final String SENHA = "password";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/minecraft";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static Connection con;

    public ConexaoMySQL() throws ClassNotFoundException, SQLException {
        openConnection();
        createGamePlayers();
    }

    public static java.sql.Connection openConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        con = DriverManager.getConnection(URL, USUARIO, SENHA);
        return con;
    }

    public static void closeConnection() throws SQLException {
        con.close();
    }

    public static void createGamePlayers() throws SQLException {
        Statement stmt = con.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS gameplayer ("
                + "gameplayer VARCHAR(40) NOT NULL PRIMARY KEY,"
                + "money INTEGER NOT NULL DEFAULT 0,"
                + "kills INTEGER NOT NULL DEFAULT 0,"
                + "deaths INTEGER NOT NULL DEFAULT 0"
                + ");";
        stmt.execute(sql);

        sql = ("CREATE TABLE IF NOT EXISTS bans ("
                + "gameplayer VARCHAR(40) NOT NULL PRIMARY KEY,"
                + "banned_by VARCHAR(40) NOT NULL,"
                + "reason VARCHAR(50) NOT NULL,"
                + "banned_at DATE NOT NULL,"
                + "unbanned_at DATE,"
                + "FOREIGN KEY(gameplayer) REFERENCES gameplayer(gameplayer),"
                + "FOREIGN KEY(banned_by) REFERENCES gameplayer(gameplayer)"
                + ");");

        stmt.execute(sql);
    }
}
