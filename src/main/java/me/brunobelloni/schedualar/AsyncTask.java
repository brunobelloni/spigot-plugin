package me.brunobelloni.schedualar;

import static me.brunobelloni.controllers.CooldownController.removeCooldown;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AsyncTask extends BukkitRunnable {

    private Player p;

    public AsyncTask(Player p) {
        this.p = p;
    }

    @Override
    public void run() {
        removeCooldown(p);
    }
}
