package me.brunobelloni.listeners.player;

import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.game.GamePlayer;
import static me.brunobelloni.controllers.PlayerController.playerDataHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    public HashMap<UUID, GamePlayer> playerData;

    public QuitEvent() {
        this.playerData = playerDataHandler;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

}
