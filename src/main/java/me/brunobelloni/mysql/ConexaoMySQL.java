package me.brunobelloni.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import org.bukkit.entity.Player;

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

        String sql = "CREATE TABLE IF NOT EXISTS player ("
                + "id     VARCHAR(40) NOT NULL,"
                + "money  INTEGER     NOT NULL DEFAULT 0,"
                + "kills  INTEGER     NOT NULL DEFAULT 0,"
                + "deaths INTEGER     NOT NULL DEFAULT 0,"
                + "PRIMARY KEY (id)"
                + ");";
        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS bans ("
                + "player  VARCHAR(40) NOT NULL,"
                + "banned_by   VARCHAR(40) NOT NULL,"
                + "reason      VARCHAR(50) NOT NULL,"
                + "banned_at   DATE        NOT NULL,"
                + "unbanned_at DATE,"
                + "PRIMARY KEY(player),"
                + "FOREIGN KEY(player)    REFERENCES player(id),"
                + "FOREIGN KEY(banned_by) REFERENCES player(id)"
                + ");";
        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS kit ("
                + "id        SERIAL      NOT NULL PRIMARY KEY,"
                + "descricao VARCHAR(40) NOT NULL,"
                + "PRIMARY KEY(id)"
                + ");";
        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS player_kit ("
                + "player  VARCHAR(40) NOT NULL,"
                + "kit     INTEGER     NOT NULL,"
                + "PRIMARY KEY(player, kit),"
                + "FOREIGN KEY(player)    REFERENCES player(id),"
                + "FOREIGN KEY(banned_by) REFERENCES player(id)"
                + ");";
        stmt.execute(sql);

    }

    public void insert(Player player) throws ClassNotFoundException, SQLException {
        UUID uuid = player.getUniqueId();

        String sql = "INSERT OR IGNORE INTO gamer(id) VALUES('" + uuid + "');";

        Statement stmt = con.createStatement();
        stmt.execute(sql);

        con.close();
    }
}
