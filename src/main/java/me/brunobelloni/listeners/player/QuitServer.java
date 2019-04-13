package me.brunobelloni.listeners.player;

import static me.brunobelloni.controllers.MenuController.removeMenuOnPlayerQuit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitServer implements Listener {

    public QuitServer() {
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        removeMenuOnPlayerQuit(e.getPlayer());
    }
}
