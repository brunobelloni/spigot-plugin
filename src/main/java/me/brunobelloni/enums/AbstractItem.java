package me.brunobelloni.enums;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AbstractItem {

    public AbstractItem() {

    }

    public static ItemStack createItem(Material material, String name, String[] lore) {
        return createItem(material, (short) 0, name, lore);
    }

    public static ItemStack createItem(Material material, short type, String name, String[] lore) {
        ItemStack itemStack = new ItemStack(material, 1, type);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        List<String> coloredLore = new ArrayList<>();
        for (String s : lore) {
            coloredLore.add(ChatColor.translateAlternateColorCodes('&', s));
        }

        itemMeta.setLore(coloredLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
