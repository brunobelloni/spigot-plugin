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
    public void neverRain(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void foodLevelChange(FoodLevelChangeEvent event) {
        event.setFoodLevel(20);
    }
    
       @EventHandler
    public void creatureSpawn(CreatureSpawnEvent event) {
        event.setCancelled(true);
    }
}
