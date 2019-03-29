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

public enum Messages {

    DONT_HAVE_PERMISSION("&cVocê não tem permissão!"),
    CHOOSE_KIT("&7Você escolheu o kit &c"),
    KIT_CHEST_NAME("&cSelecione seu Kit");

    private final String message;

    private Messages(String message) {
        this.message = ChatColor.translateAlternateColorCodes('&', message);
    }

    @Override
    public String toString() {
        return this.message;
    }
}
