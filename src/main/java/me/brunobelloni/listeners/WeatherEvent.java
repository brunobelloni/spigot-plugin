package me.brunobelloni.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherEvent implements Listener {

    public WeatherEvent() {
    }

    @EventHandler
    public void neverRain(WeatherChangeEvent event) {
        event.setCancelled(true);
    }
}
