package me.brunobelloni.api.kits;

import me.brunobelloni.game.GamePlayer;
import org.bukkit.scheduler.BukkitRunnable;

public class CooldownAPI extends BukkitRunnable {

    private GamePlayer gp;

    public CooldownAPI(GamePlayer gp) {
        this.gp = gp;
    }

    @Override
    public void run() {
        gp.removeCooldown();
    }
}
