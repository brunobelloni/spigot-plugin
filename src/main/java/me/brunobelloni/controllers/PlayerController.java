package me.brunobelloni.controllers;

import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.Plugin;
import static me.brunobelloni.Plugin.database;
import me.brunobelloni.game.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerController implements Listener {

    private Plugin plugin;
    public static HashMap<UUID, GamePlayer> onlinePlayersController;

    public PlayerController(Plugin plugin) {
        this.plugin = plugin;
        onlinePlayersController = new HashMap<>();
    }

    @EventHandler
    public GamePlayer onPlayerLogin(PlayerLoginEvent e) throws Exception {
        Player p = e.getPlayer();

        database.insert(p);
        GamePlayer g = database.select(p);
        return onlinePlayersController.put(g.getUUID(), g);
    }

    @EventHandler
    public GamePlayer onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        return onlinePlayersController.remove(p.getUniqueId());
    }
}
