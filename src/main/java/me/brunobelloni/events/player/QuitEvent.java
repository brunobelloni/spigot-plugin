package me.brunobelloni.events.player;

import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.Plugin;
import me.brunobelloni.types.Gamer;
import static me.brunobelloni.types.HashHandler.playerDataHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    private Plugin plugin;
    public HashMap<UUID, Gamer> playerData;

    public QuitEvent(Plugin plugin) {
        this.plugin = plugin;
        this.playerData = playerDataHandler;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

}
