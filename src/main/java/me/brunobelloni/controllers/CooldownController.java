package me.brunobelloni.controllers;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.entity.Player;

public class CooldownController {

    private static final HashMap<UUID, Long> playersWithCooldown = new HashMap<>();

    public CooldownController() {
    }

    public static boolean isOnCooldown(Player p) {
        return true;
    }

    public static void putCooldown(Player p, long cooldown) {
        playersWithCooldown.put(p.getUniqueId(), cooldown);
    }

    public static void removeCooldown(Player p) {
        playersWithCooldown.remove(p.getUniqueId());

    }

    public static long getCooldown(Player p) {
        return playersWithCooldown.get(p.getUniqueId());
    }
}
