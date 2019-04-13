package me.brunobelloni.enums;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItem {

    public static ItemStack KIT_ITEM = createItem(Material.CHEST, "&cKit Menu", new String[]{"", ""});
    public static ItemStack SOUP = createItem(Material.MUSHROOM_SOUP, "&cSoup", new String[]{"", "&7Beba para curar 3.5 corações de vida"});
    public static ItemStack BOWL = createItem(Material.BOWL, "&cBowl", new String[]{"", "&7Pote de sopa"});
    public static ItemStack DIAMOND_SWORD = createItem(Material.DIAMOND_SWORD, "&cDiamond Sword", new String[]{"", ""});
    public static ItemStack IRON_SWORD = createItem(Material.IRON_SWORD, "&cIron Sword", new String[]{"", ""});
    public static ItemStack THOR_ITEM = createItem(Material.WOOD_AXE, "&cThor", new String[]{"", ""});

    private static ItemStack createItem(Material material, String name, String[] lore) {
        ItemStack itemStack = new ItemStack(material);
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
