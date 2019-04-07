package me.brunobelloni.api.chest;

import me.brunobelloni.api.chest.IconMenu.Row;
import me.brunobelloni.api.chest.IconMenu.onClick;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommonChestMenu {

    private static IconMenu menu = new IconMenu("IconMenu", 2, new onClick() {
        @Override
        public boolean click(Player p, IconMenu menu, Row row, int slot, ItemStack item) {
            if (row.getRow() == 1) {
                Bukkit.broadcastMessage(row.getRowItem(slot).getType().name());
            }
            return true;
        }
    });

    public CommonChestMenu() {
    }

    public static void setCommonChestMenu() {
        menu.addButton(menu.getRow(1), 0, new ItemStack(Material.STONE), "Stone Button ;)");
        menu.addButton(menu.getRow(1), 1, new ItemStack(Material.WOOD), "Wood Button ;)");
        menu.addButton(menu.getRow(1), 2, new ItemStack(Material.DIAMOND), "Diamond Button ;)");
        menu.addButton(menu.getRow(1), 3, new ItemStack(Material.GOLD_BLOCK), "Gold Button ;)");
        menu.addButton(menu.getRow(1), 4, new ItemStack(Material.IRON_BLOCK), "Iron Button ;)");
        menu.addButton(menu.getRow(1), 5, new ItemStack(Material.OBSIDIAN), "Obby Button ;)");
        menu.addButton(menu.getRow(1), 6, new ItemStack(Material.ANVIL), "Anvil Button ;)");
        menu.addButton(menu.getRow(1), 7, new ItemStack(Material.STONE_BUTTON), "Button Button ;)");
        menu.addButton(menu.getRow(1), 8, new ItemStack(Material.PORTAL), "Portal Button ;)");
    }

    public static IconMenu getMenu() {
        return menu;
    }
}
