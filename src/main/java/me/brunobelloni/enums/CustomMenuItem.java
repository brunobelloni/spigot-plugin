package me.brunobelloni.enums;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum CustomMenuItem {

    HAS_PVP(Material.DIAMOND_SWORD, "&APVP", new String[]{"", ""}),
    DONT_HAS_PVP(Material.DIAMOND_SWORD, "&CPVP", new String[]{"", ""}),
    HAS_THOR(Material.WOOD_AXE, "&aTHOR", new String[]{"", ""}),
    DONT_HAS_THOR(Material.WOOD_AXE, "&cTHOR", new String[]{"", ""});

    private ItemStack item;

    private CustomMenuItem(Material material, String name, String[] lore) {
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
