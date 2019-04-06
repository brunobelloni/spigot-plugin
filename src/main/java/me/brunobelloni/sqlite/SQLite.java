package me.brunobelloni.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import me.brunobelloni.Plugin;

public class SQLite {

    private Plugin plugin;
    private static Connection con;
    private static boolean hasData = false;
    private String url;

    public SQLite(Plugin plugin) throws ClassNotFoundException, SQLException {
        this.plugin = plugin;
        this.url = "jdbc:sqlite:" + plugin.getDataFolder() + "/data.db";
        this.initialize();
    }

    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");

        con = DriverManager.getConnection(url);
    }

    private void initialize() throws ClassNotFoundException, SQLException {
        getConnection();

        String sql = "CREATE TABLE IF NOT EXISTS gamer ("
                + "id UUID PRIMARY KEY, "
                + "money INTEGER NOT NULL DEFAULT 0, "
                + "kills INTEGER NOT NULL DEFAULT 0, "
                + "deaths INTEGER NOT NULL DEFAULT 0 "
                + ");";

        Statement stmt = con.createStatement();
        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS bans ("
                + "gamer UUID NOT NULL, "
                + "reason TEXT NOT NULL, "
                + "banned_by UUID NOT NULL, "
                + "banned_at DATE NOT NULL, "
                + "unbanned_at DATE, "
                + "FOREIGN KEY(gamer) REFERENCES gamer(id), "
                + "FOREIGN KEY(banned_by) REFERENCES gamer(id), "
                + "PRIMARY KEY(gamer)"
                + ");";
        stmt = con.createStatement();
        stmt.execute(sql);

        con.close();
    }

    public void insert(UUID uuid) throws ClassNotFoundException, SQLException {
        String sql = "INSERT OR IGNORE INTO gamer(id) VALUES('" + uuid + "')";

        getConnection();

        PreparedStatement state = con.prepareStatement(sql);
        state.executeUpdate();

        con.close();
    }

}
