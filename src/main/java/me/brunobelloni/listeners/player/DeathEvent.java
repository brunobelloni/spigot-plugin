package me.brunobelloni.listeners.player;

import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.Plugin;
import me.brunobelloni.game.GamePlayer;
import static me.brunobelloni.controllers.PlayerController.playerDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathEvent implements Listener {

    private Plugin plugin;
    public HashMap<UUID, GamePlayer> playerData;

    public DeathEvent() {
        this.plugin = (Plugin) Bukkit.getPluginManager().getPlugins()[0];
        this.playerData = playerDataHandler;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.setDeathMessage(null);

        final Player p = e.getEntity();
        GamePlayer g = playerData.get(p.getUniqueId());
        g.removeAbility();
        p.sendMessage("Você morreu!");

        new BukkitRunnable() {
            @Override
            public void run() {
                p.spigot().respawn();
            }
        }.runTaskLater(plugin, 1);
    }

    @EventHandler
    public void PlayerRespawn(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        GamePlayer g = playerData.get(p.getUniqueId());
        g.clearInventory();
        g.giveMenuItens();
        p.sendMessage("Você renasceu!");
    }
}
