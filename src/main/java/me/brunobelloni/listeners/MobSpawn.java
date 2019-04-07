package me.brunobelloni.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobSpawn implements Listener {

    public MobSpawn() {
    }

    @EventHandler
    public void creatureSpawn(CreatureSpawnEvent event) {
        event.setCancelled(true);
    }
}
