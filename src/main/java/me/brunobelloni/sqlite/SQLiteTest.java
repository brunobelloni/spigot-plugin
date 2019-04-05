package me.brunobelloni.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import me.brunobelloni.Plugin;

public class SQLiteTest {

    private Plugin plugin;
    private static Connection con;
    private static boolean hasData = false;

    public SQLiteTest(Plugin plugin) {
        this.plugin = plugin;
    }

    public ResultSet displayUsers() throws ClassNotFoundException, SQLException {
        if (con == null) {
            getConnection();
        }

        Statement state = con.createStatement();
        ResultSet res = state.executeQuery("SELECT fname, lname FROM user");
        return res;
    }

    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:" + plugin.getDataFolder()
                + "/SQLiteTeste1.db");
        initialize();
    }

    private void initialize() throws ClassNotFoundException, SQLException {
        if (!hasData) {
            hasData = true;

            Statement state = con.createStatement();
            ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='user'");

            if (!res.next()) {
                System.out.println("Building the User table with prepolutated values");

                Statement state2 = con.createStatement();
                state2.execute("CREATE TABLE user(id integer, fName varchar(60), lName varchar(60), primary key(id);");

                PreparedStatement prep = con.prepareStatement("INSERT INTO user values(?, ?, ?);");
                prep.setString(2, "Jhon");
                prep.setString(3, "McNeil");
                prep.execute();
            }
        }
    }

    public void addUser(String firstName, String lastName) throws ClassNotFoundException, SQLException {
        if (con == null) {
            getConnection();
        }

        PreparedStatement prep = con.prepareStatement("INSERT INTO user values(?, ?, ?);");
        prep.setString(2, firstName);
        prep.setString(3, lastName);
        prep.execute();
    }

}
