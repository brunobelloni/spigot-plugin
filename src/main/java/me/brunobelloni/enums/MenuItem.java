package me.brunobelloni.enums;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuItem {

    public static ItemStack GLASS = createItem(Material.STAINED_GLASS_PANE, (short) 15, " ", new String[]{""});

    public static ItemStack HAS_PVP = createItem(Material.DIAMOND_SWORD, "&APVP", new String[]{"", ""});
    public static ItemStack DONT_HAS_PVP = createItem(Material.DIAMOND_SWORD, "&CPVP", new String[]{"", ""});

    public static ItemStack HAS_THOR = createItem(Material.WOOD_AXE, "&aTHOR", new String[]{"", ""});
    public static ItemStack DONT_HAS_THOR = createItem(Material.WOOD_AXE, "&cTHOR", new String[]{"", ""});

    public static ItemStack HAS_SWITCHER = createItem(Material.SNOW_BALL, "&aSWITCHER", new String[]{"", ""});
    public static ItemStack DONT_HAS_SWITCHER = createItem(Material.SNOW_BALL, "&cSWITCHER", new String[]{"", ""});

    private static ItemStack createItem(Material material, String name, String[] lore) {
        return createItem(material, (short) 0, name, lore);
    }

    private static ItemStack createItem(Material material, short type, String name, String[] lore) {
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
