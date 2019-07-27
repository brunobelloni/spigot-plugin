package me.brunobelloni.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.brunobelloni.controllers.MenuController.removeMenuOnPlayerQuit;

public class QuitServer implements Listener {

    public QuitServer() {
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        removeMenuOnPlayerQuit(e.getPlayer());
    }
}
