package me.brunobelloni.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class UtilListeners implements Listener {

    public UtilListeners() {
    }

    @EventHandler
    public void neverRain(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void foodLevelChange(FoodLevelChangeEvent e) {
        e.setFoodLevel(20);
    }
    
       @EventHandler
    public void creatureSpawn(CreatureSpawnEvent e) {
        e.setCancelled(true);
    }
}
