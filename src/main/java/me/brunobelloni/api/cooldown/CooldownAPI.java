package me.brunobelloni.api.cooldown;

import static me.brunobelloni.controllers.CooldownController.removeCooldown;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CooldownAPI extends BukkitRunnable {

    private Player p;

    public CooldownAPI(Player p) {
        this.p = p;
    }

    @Override
    public void run() {
        removeCooldown(p);
    }
}
