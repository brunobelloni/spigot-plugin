package me.brunobelloni.mysql;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import me.brunobelloni.Plugin;
import org.bukkit.configuration.file.FileConfiguration;

public class Database {

    private Plugin instance;
    public static HikariDataSource hikari;

    public Database(Plugin plugin) throws ClassNotFoundException, SQLException {
        this.instance = instance;
        this.configureDatabase();
        this.createTables();
        this.createFunctions();
    }

    public static HikariDataSource getHikari() {
        return hikari;
    }

    public void configureDatabase() {
        FileConfiguration config = instance.getConfig();
        hikari = new HikariDataSource();
        hikari.setMaximumPoolSize(10);

        hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        hikari.addDataSourceProperty("port", config.getString("database.port"));
        hikari.addDataSourceProperty("user", config.getString("database.user"));
        hikari.addDataSourceProperty("serverName", config.getString("database.host"));
        hikari.addDataSourceProperty("password", config.getString("database.password"));
        hikari.addDataSourceProperty("databaseName", config.getString("database.database"));

        hikari.addDataSourceProperty("cachePrepStmts", config.getBoolean("database.cachePrepStmts"));
        hikari.addDataSourceProperty("cachePrepStmts", config.getBoolean("database.cachePrepStmts"));
        hikari.addDataSourceProperty("prepStmtCacheSize", config.getInt("database.prepStmtCacheSize"));
        hikari.addDataSourceProperty("prepStmtCacheSqlLimit", config.getInt("database.prepStmtCacheSqlLimit"));
        hikari.addDataSourceProperty("useServerPrepStmts", config.getBoolean("database.useServerPrepStmts"));
        hikari.addDataSourceProperty("useLocalSessionState", config.getBoolean("database.useLocalSessionState"));
        hikari.addDataSourceProperty("rewriteBatchedStatements", config.getBoolean("database.rewriteBatchedStatements"));
        hikari.addDataSourceProperty("cacheResultSetMetadata", config.getBoolean("database.cacheResultSetMetadata"));
        hikari.addDataSourceProperty("cacheServerConfiguration", config.getBoolean("database.cacheServerConfiguration"));
        hikari.addDataSourceProperty("elideSetAutoCommits", config.getBoolean("database.elideSetAutoCommits"));
        hikari.addDataSourceProperty("maintainTimeStats", config.getBoolean("database.maintainTimeStats"));
    }

    public static void closeConnection() throws SQLException {
        hikari.close();
    }

    public void createTables() throws SQLException {
        Connection con = hikari.getConnection();
        Statement stmt = con.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS player ("
                + "id     VARCHAR(36) NOT NULL,"
                + "money  INTEGER     NOT NULL DEFAULT 0,"
                + "kills  INTEGER     NOT NULL DEFAULT 0,"
                + "deaths INTEGER     NOT NULL DEFAULT 0,"
                + "PRIMARY KEY (id)"
                + ");";
        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS bans ("
                + "player  VARCHAR(36) NOT NULL,"
                + "banned_by   VARCHAR(36) NOT NULL,"
                + "reason      VARCHAR(40) NOT NULL,"
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
                + "player  VARCHAR(36) NOT NULL,"
                + "kit     SERIAL     NOT NULL,"
                + "PRIMARY KEY(player, kit),"
                + "FOREIGN KEY(player) REFERENCES player(id),"
                + "FOREIGN KEY(kit)    REFERENCES kit(id)"
                + ");";
        stmt.execute(sql);
    }

    private void createFunctions() throws SQLException {
        Connection con = hikari.getConnection();
        Statement stmt = con.createStatement();

        String sql = "CREATE OR REPLACE FUNCTION addDeath(uuid VARCHAR(36))\n"
                + "    RETURNS VARCHAR(36)\n"
                + "    DETERMINISTIC\n"
                + "BEGIN\n"
                + "    DECLARE p_deaths INTEGER;\n"
                + "\n"
                + "    SELECT deaths FROM player WHERE id=uuid INTO p_deaths;\n"
                + "    UPDATE player SET deaths=p_deaths+1 WHERE id=uuid;\n"
                + "    RETURN uuid;"
                + "END";

        stmt.execute(sql);
    }
}
