package me.brunobelloni.controllers;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.entity.Player;

public class CooldownController {

    private static HashMap<UUID, Long> playersWithCooldown;

    public CooldownController() {
        playersWithCooldown = new HashMap<>();
    }

    public static boolean isOnCooldown(Player p) {
        return playersWithCooldown.get(p.getUniqueId()) != null;
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
