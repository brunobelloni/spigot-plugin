package me.brunobelloni.game;

import me.brunobelloni.enums.Abilitys;
import me.brunobelloni.enums.CustomItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GamePlayer {

    public GamePlayer() {
    }

    public static void setAbility(Player p, Abilitys ability) {
    }

    public static Abilitys getAbility(Player p) {
        return null;
    }

    public static void removeAbility(Player p) {
        // remove ability
    }

    public static void fillInventoryWithSoup(Player p) {
        ItemStack[] inventory = p.getInventory().getContents();
        ItemStack soup = CustomItem.SOUP.getItem();

        for (ItemStack item : inventory) {
            if (item == null) {
                p.getInventory().addItem(soup);
            }
        }
    }

    public static void giveMenuItens(Player p) {
        p.sendMessage("Implementar os itens iniciais!");
    }

    public static boolean isOnCooldown(Player p) {
        return true;
    }

    public static void putCooldown(Player p, long cooldown) {

    }

    public static void removeCooldown(Player p) {

    }

    public static long getCooldown(Player p) {
        return 0;
    }

    public static void addDeath(Player p) {
        // this.deaths += 1;
    }

}
