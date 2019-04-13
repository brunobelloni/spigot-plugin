package me.brunobelloni.enums;

import org.bukkit.ChatColor;

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
