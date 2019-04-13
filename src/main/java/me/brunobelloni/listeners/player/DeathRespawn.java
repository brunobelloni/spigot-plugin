package me.brunobelloni.listeners.player;

import java.sql.SQLException;
import me.brunobelloni.Plugin;
import static me.brunobelloni.controllers.AbilityController.removeAbility;
import static me.brunobelloni.controllers.CooldownController.removeCooldown;
import static me.brunobelloni.controllers.PlayerController.addDeath;
import static me.brunobelloni.controllers.PlayerController.giveMenuItens;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathRespawn implements Listener {

    private Plugin instance;

    public DeathRespawn() {
        this.instance = (Plugin) Bukkit.getPluginManager().getPlugins()[0];
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) throws SQLException {
        e.setDeathMessage(null);

        final Player p = e.getEntity();

        removeAbility(p);
        removeCooldown(p);
        addDeath(p);

        new BukkitRunnable() {
            @Override
            public void run() {
                p.spigot().respawn();
            }
        }.runTaskLater(instance, 1);
    }

    @EventHandler
    public void PlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        p.getInventory().clear();
        giveMenuItens(p);
    }
}
