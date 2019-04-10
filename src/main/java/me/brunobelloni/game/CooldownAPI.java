package me.brunobelloni.game;

import static me.brunobelloni.game.GamePlayer.removeCooldown;
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
