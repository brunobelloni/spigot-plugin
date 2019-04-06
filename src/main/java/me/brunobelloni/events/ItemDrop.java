package me.brunobelloni.events;

import me.brunobelloni.Plugin;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemDrop implements Listener {

    private Plugin plugin;

    public ItemDrop(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void itemDrop(final ItemSpawnEvent e) {
        new BukkitRunnable() {
            @Override
            public void run() {
                e.getEntity().remove();
                e.getLocation().getWorld().playEffect(e.getEntity().getLocation(), Effect.SMOKE, 10);
                e.getLocation().getWorld().playSound(e.getEntity().getLocation(), Sound.LAVA_POP, 1.0F, 1.0F);
            }
        }.runTaskLater(plugin, 5 * 20);
    }
}
