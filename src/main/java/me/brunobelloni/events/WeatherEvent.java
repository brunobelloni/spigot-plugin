package me.brunobelloni.events;

import me.brunobelloni.Plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherEvent implements Listener {

    private Plugin plugin;

    public WeatherEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void neverRain(WeatherChangeEvent event) {
        event.setCancelled(true);
    }
}
