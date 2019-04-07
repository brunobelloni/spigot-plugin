package me.brunobelloni.api.event;

import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.Plugin;
import static me.brunobelloni.controllers.PlayerController.onlinePlayersController;
import me.brunobelloni.game.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventListener implements Listener {

    protected Plugin plugin;
    protected HashMap<UUID, GamePlayer> onlinePlayers;

    public EventListener() {
        this.plugin = (Plugin) Bukkit.getPluginManager().getPlugins()[0];
        this.onlinePlayers = onlinePlayersController;
    }

    @EventHandler
    public void soupUseEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.getItemInHand().getType().equals(Material.MUSHROOM_SOUP)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (p.getHealth() != 20.0D && p.getHealth() + 1.0D <= 20.0D) {
                    e.setCancelled(true);
                    GamePlayer gp = onlinePlayers.get(p.getUniqueId());
                    Bukkit.getPluginManager().callEvent(new SoupEvent(e, gp));
                }
            }
        }
    }
}
