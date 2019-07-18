package me.brunobelloni.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Item extends  AbstractItem{

    public static final ItemStack KIT_ITEM = createItem(Material.CHEST, "&cKit Menu", new String[]{"", ""});
    public static final ItemStack SOUP = createItem(Material.MUSHROOM_SOUP, "&cSoup", new String[]{"", "&7Beba para curar 3.5 corações de vida"});
    public static final ItemStack BOWL = createItem(Material.BOWL, "&cBowl", new String[]{"", "&7Pote de sopa"});
    public static final ItemStack DIAMOND_SWORD = createItem(Material.DIAMOND_SWORD, "&cDiamond Sword", new String[]{"", ""});
    public static final ItemStack IRON_SWORD = createItem(Material.IRON_SWORD, "&cIron Sword", new String[]{"", ""});
    public static final ItemStack THOR_ITEM = createItem(Material.WOOD_AXE, "&cThor", new String[]{"", ""});
    public static final ItemStack SWITCHER_ITEM = createItem(Material.SNOW_BALL, "&cSwitcher", new String[]{"", ""});
    public static final ItemStack SWITCHER_CD = createItem(Material.SLIME_BALL, "&cSwitcher Cooldown", new String[]{"", ""});
}
