package me.brunobelloni.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import me.brunobelloni.Plugin;
import org.bukkit.entity.Player;

public class Database {

    private static Plugin plugin;
    private static String user;
    private static String port;
    private static String host;
    private static String password;
    private static String database;
    private static String url;
    private static String driver;

    private static Connection con;

    public Database(Plugin plugin) throws ClassNotFoundException, SQLException {
        this.plugin = plugin;
        configureDatabase();
        createGamePlayers();
    }

    public static void configureDatabase() {
        user = plugin.getConfig().getString("database.user");
        port = plugin.getConfig().getString("database.port");
        host = plugin.getConfig().getString("database.host");
        password = plugin.getConfig().getString("database.password");
        database = plugin.getConfig().getString("database.database");
        url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        driver = "com.mysql.jdbc.Driver";
    }

    public static void openConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        con = DriverManager.getConnection(url, user, password);
    }

    public static void closeConnection() throws SQLException {
        con.close();
    }

    public static void createGamePlayers() throws SQLException, ClassNotFoundException {
        openConnection();
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
                + "id        SERIAL      NOT NULL,"
                + "descricao VARCHAR(40) NOT NULL,"
                + "PRIMARY KEY(id)"
                + ");";
        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS player_kit ("
                + "player  VARCHAR(40) NOT NULL,"
                + "kit     SERIAL     NOT NULL,"
                + "PRIMARY KEY(player, kit),"
                + "FOREIGN KEY(player) REFERENCES player(id),"
                + "FOREIGN KEY(kit)    REFERENCES kit(id)"
                + ");";
        stmt.execute(sql);
        closeConnection();
    }

    public void insert(Player player) throws ClassNotFoundException, SQLException {
        UUID uuid = player.getUniqueId();

        String sql = "INSERT IGNORE INTO player(id) VALUES('" + uuid + "');";

        openConnection();
        Statement stmt = con.createStatement();
        stmt.execute(sql);

        con.close();
    }

//    public void update(GamePlayer gp) throws ClassNotFoundException, SQLException {
//        UUID uuid = gp.getUUID();
//
//        String sql = "UPDATE player "
//                + "SET money='" + gp.getMoney() + "',"
//                + "kills='" + gp.getKills() + "',"
//                + "deaths='" + gp.getDeaths() + "' "
//                + "WHERE id='" + uuid + "';";
//
//        openConnection();
//        Statement stmt = con.createStatement();
//        stmt.execute(sql);
//
//        System.out.println("atualizou " + uuid);
//
//        con.close();
//    }
//    public GamePlayer select(Player player) throws ClassNotFoundException, SQLException {
//        UUID uuid = player.getUniqueId();
//
//        String sql = "SELECT p.money, p.kills, p.deaths "
//                + "FROM player p "
//                + "WHERE id = '" + uuid + "';";
//
//        openConnection();
//
//        Statement stmt = con.createStatement();
//        ResultSet rs = stmt.executeQuery(sql);
//
//        GamePlayer g = new GamePlayer(player);
//        while (rs.next()) {
//            g.setMoney(rs.getInt("money"));
//            g.setKills(rs.getInt("kills"));
//            g.setDeaths(rs.getInt("deaths"));
//        }
//
//        System.out.println(g.getMoney());
//        System.out.println(g.getKills());
//        System.out.println(g.getDeaths());
//
//        con.close();
//        return g;
//    }
}
