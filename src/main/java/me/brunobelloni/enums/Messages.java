package me.brunobelloni.enums;

import org.bukkit.ChatColor;

/**
 * @BukkitColorGuide
 * 
 * &0 - Black           &1 - Dark Blue
 * &2 - Dark Green      &3 - Dark Aqua
 * &4 - Dark Red        &5 - Dark Purple
 * &6 - Gold            &7 - Gray
 * &8 - Dark Grey       &9 - Blue
 * &a - Green           &b - Aqua
 * &c - Red             &d - Light Purple
 * &e - Yellow          &f - Withe
 * 
 * &o - Italic          &l - Bold
 * &n - Underline       &m - Striketrhough
 * &k - 01ain           &r - Reset
 */
public class Messages {

    public static String DONT_HAVE_PERMISSION = colorize("&cVocê não tem permissão!");
    public static String CHOOSE_KIT = colorize("&7Você escolheu o kit &c");
    public static String KIT_CHEST_NAME = colorize("&cSelecione seu Kit");
    public static String COOLDOWN_WARNING_BEFORE = colorize("&cEspere ");
    public static String COOLDOWN_WARNING_AFTER = colorize("&c segundos para usar novamente!");
    public static String COMMAND_FROM_CONSOLE = colorize("&cNão execute esse comando do console!");

    private static String colorize(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
