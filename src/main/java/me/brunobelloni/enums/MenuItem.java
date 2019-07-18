package me.brunobelloni.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MenuItem extends AbstractItem{

    public static final ItemStack GLASS = createItem(Material.STAINED_GLASS_PANE, (short) 15, " ", new String[]{""});

    public static final ItemStack HAS_PVP = createItem(Material.DIAMOND_SWORD, "&APVP", new String[]{"", ""});
    public static final ItemStack DONT_HAS_PVP = createItem(Material.DIAMOND_SWORD, "&CPVP", new String[]{"", ""});

    public static final ItemStack HAS_THOR = createItem(Material.WOOD_AXE, "&aTHOR", new String[]{"", ""});
    public static final ItemStack DONT_HAS_THOR = createItem(Material.WOOD_AXE, "&cTHOR", new String[]{"", ""});

    public static final ItemStack HAS_SWITCHER = createItem(Material.SNOW_BALL, "&aSWITCHER", new String[]{"", ""});
    public static final ItemStack DONT_HAS_SWITCHER = createItem(Material.SNOW_BALL, "&cSWITCHER", new String[]{"", ""});

}
