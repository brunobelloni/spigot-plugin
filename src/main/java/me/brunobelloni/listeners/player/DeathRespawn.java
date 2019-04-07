package me.brunobelloni.listeners.player;

import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.Plugin;
import static me.brunobelloni.controllers.PlayerController.onlinePlayersController;
import me.brunobelloni.game.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathRespawn implements Listener {

    private Plugin plugin;
    public HashMap<UUID, GamePlayer> onlinePlayers;

    public DeathRespawn() {
        this.plugin = (Plugin) Bukkit.getPluginManager().getPlugins()[0];
        this.onlinePlayers = onlinePlayersController;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.setDeathMessage(null);

        final Player p = e.getEntity();
        GamePlayer g = onlinePlayers.get(p.getUniqueId());
        g.removeAbility();

        new BukkitRunnable() {
            @Override
            public void run() {
                p.spigot().respawn();
            }
        }.runTaskLater(plugin, 1);
    }

    @EventHandler
    public void PlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        GamePlayer g = onlinePlayers.get(p.getUniqueId());
        g.clearInventory();
        g.giveMenuItens();
    }
}
