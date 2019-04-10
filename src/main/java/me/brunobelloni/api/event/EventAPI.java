package me.brunobelloni.api.event;

import me.brunobelloni.Plugin;
import static me.brunobelloni.enums.CustomItem.SOUP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventAPI implements Listener {

    protected Plugin plugin;

    public EventAPI() {
        this.plugin = (Plugin) Bukkit.getPluginManager().getPlugins()[0];
    }

    @EventHandler
    public void soupUseEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (p.getItemInHand().equals(SOUP.getItem())) {
                if (p.getHealth() != 20.0D && p.getHealth() + 1.0D <= 20.0D) {
                    e.setCancelled(true);
                    Bukkit.getPluginManager().callEvent(new SoupEvent(e, p));
                }
            }
        }
    }
}
