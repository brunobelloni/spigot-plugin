package me.brunobelloni.events.player;

import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.Plugin;
import me.brunobelloni.types.Gamer;
import static me.brunobelloni.types.HashHandler.playerDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathEvent implements Listener {

    private Plugin plugin;
    public HashMap<UUID, Gamer> playerData;

    public DeathEvent(Plugin plugin) {
        this.plugin = plugin;
        this.playerData = playerDataHandler;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.setDeathMessage(null);

        final Player p = e.getEntity();
        Gamer g = playerData.get(p.getUniqueId());
        g.removeAbility();
        p.sendMessage("Você morreu!");

        new BukkitRunnable() {
            @Override
            public void run() {
                p.spigot().respawn();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugins()[0], 1);
    }

    @EventHandler
    public void PlayerRespawn(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        Gamer g = playerData.get(p.getUniqueId());
        g.clearInventory();
        g.giveMenuItens();
        p.sendMessage("Você renasceu!");
    }
}
