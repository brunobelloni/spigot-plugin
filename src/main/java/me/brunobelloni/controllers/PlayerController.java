package me.brunobelloni.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import static me.brunobelloni.enums.Item.KIT_ITEM;
import static me.brunobelloni.enums.Item.SOUP;
import static me.brunobelloni.mysql.Database.getHikari;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerController {

    private static final String insert = "INSERT IGNORE INTO player(id) VALUES(?);";
    private static final String select = "SELECT p.money, p.kills, p.deaths FROM player p WHERE id=?;";

    public PlayerController() {
    }

    public static ArrayList<String> getStats(Player p) {
        ArrayList<String> stats = new ArrayList<>();

        return stats;
    }

    public static void asyncInsertPlayer(Player p) throws SQLException {
        Connection con = getHikari().getConnection();
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setString(1, p.getUniqueId().toString());
        ps.execute();

        if (con != null) {
            con.close();
        }
    }

    public static void addDeath(Player p) throws SQLException {
        Connection con = getHikari().getConnection();
        String update = "SELECT addDeath(?);";
        PreparedStatement ps = con.prepareStatement(update);
        ps.setString(1, p.getUniqueId().toString());
        ps.execute();

        if (con != null) {
            con.close();
        }
    }

    public static void fillInventoryWithSoup(Player p) {
        ItemStack[] inventory = p.getInventory().getContents();
        ItemStack soup = SOUP;

        for (ItemStack item : inventory) {
            if (item == null) {
                p.getInventory().addItem(soup);
            }
        }
    }

    public static void giveMenuItens(Player p) {
        p.getInventory().addItem(KIT_ITEM);
    }
}
