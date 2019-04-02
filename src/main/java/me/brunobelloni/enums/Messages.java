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
 * &c - Red             &d - Light Purlple
 * &e - Yellow          &f - Withe
 * 
 * &o - Italic          &l - Bold
 * &n - Underline       &m - Striketrhough
 * &k - 01ain           &r - Reset
 */
public class Messages {

    public static String DONT_HAVE_PERMISSION = applyColor("&cVocê não tem permissão!");
    public static String CHOOSE_KIT = applyColor("&7Você escolheu o kit &c");
    public static String KIT_CHEST_NAME = applyColor("&cSelecione seu Kit");
    public static String COOLDOWN_WARNING = applyColor("&cEspere o tempo de recarga!");

    private static String applyColor(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
