package me.brunobelloni.enums;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum CustomItem {

    SOUP(Material.MUSHROOM_SOUP, "&cSoup"),
    DIAMOND_SWORD(Material.DIAMOND_SWORD, "&cDiamond Sword");

    private ItemStack item;

    private CustomItem(Material material, String name) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        itemStack.setItemMeta(itemMeta);
        this.item = itemStack;
    }

    public ItemStack getItem() {
        return this.item;
    }
}
