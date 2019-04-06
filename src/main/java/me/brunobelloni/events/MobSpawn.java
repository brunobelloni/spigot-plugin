package me.brunobelloni.events;

import me.brunobelloni.Plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobSpawn implements Listener {

    private Plugin plugin;

    public MobSpawn(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void creatureSpawn(CreatureSpawnEvent event) {
        event.setCancelled(true);
    }
}
