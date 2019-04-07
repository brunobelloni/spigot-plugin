package me.brunobelloni.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import me.brunobelloni.Plugin;
import me.brunobelloni.game.GamePlayer;
import org.bukkit.entity.Player;

public class Database {

    private Plugin plugin;
    private Connection con;
    private String url;

    public Database(Plugin plugin) throws ClassNotFoundException, SQLException {
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
                + "money REAL NOT NULL DEFAULT 0, "
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

    public void insert(Player player) throws ClassNotFoundException, SQLException {
        UUID uuid = player.getUniqueId();

        String sql = "INSERT OR IGNORE INTO gamer(id) VALUES('" + uuid + "');";

        getConnection();

        Statement stmt = con.createStatement();
        stmt.execute(sql);

        con.close();
    }

    public GamePlayer select(Player player) throws ClassNotFoundException, SQLException {
        UUID uuid = player.getUniqueId();

        String sql = "SELECT g.money, g.kills, g.deaths "
                + "FROM gamer g "
                + "WHERE id = '" + uuid + "';";

        getConnection();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        GamePlayer g = new GamePlayer(player);
        while (rs.next()) {
            g.setMoney(rs.getDouble("money"));
            g.setKills(rs.getInt("kills"));
            g.setDeaths(rs.getInt("deaths"));
        }

        con.close();
        return g;
    }
}
