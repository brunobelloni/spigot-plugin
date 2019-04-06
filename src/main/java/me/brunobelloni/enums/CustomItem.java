package me.brunobelloni.enums;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum CustomItem {

    SOUP(Material.MUSHROOM_SOUP, "&cSoup", new String[]{"", "&7Beba para curar 3.5 corações de vida"}),
    BOWL(Material.BOWL, "&cBowl", new String[]{"", "&7Pote de sopa"}),
    DIAMOND_SWORD(Material.DIAMOND_SWORD, "&cDiamond Sword", new String[]{"", ""}),
    THOR_ITEM(Material.WOOD_AXE, "&cThor", new String[]{"", ""});

    private ItemStack item;

    private CustomItem(Material material, String name, String[] lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        List<String> coloredLore = new ArrayList<>();
        for (String s : lore) {
            coloredLore.add(ChatColor.translateAlternateColorCodes('&', s));
        }

        itemMeta.setLore(coloredLore);
        itemStack.setItemMeta(itemMeta);
        this.item = itemStack;
    }

    public ItemStack getItem() {
        return this.item;
    }
}
