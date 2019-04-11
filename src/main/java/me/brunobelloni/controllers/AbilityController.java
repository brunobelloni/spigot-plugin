package me.brunobelloni.controllers;

import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.enums.Abilitys;
import org.bukkit.entity.Player;

public class AbilityController {

    private static HashMap<UUID, Abilitys> playersWithAbilitys;

    public AbilityController() {
        playersWithAbilitys = new HashMap<>();
    }

    public static void putAbility(Player p, Abilitys ability) {
        playersWithAbilitys.put(p.getUniqueId(), ability);
    }

    public static void removeAbility(Player p) {
        playersWithAbilitys.remove(p.getUniqueId());
    }

    public static Abilitys getAbility(Player p) {
        return playersWithAbilitys.get(p.getUniqueId());
    }

    public static boolean hasAbility(Player p) {
        return playersWithAbilitys.get(p.getUniqueId()) != null;
    }
}
