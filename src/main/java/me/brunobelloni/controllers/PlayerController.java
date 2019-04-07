package me.brunobelloni.controllers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.Plugin;
import me.brunobelloni.game.GamePlayer;
import static me.brunobelloni.Plugin.database;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerController implements Listener {

    private Plugin plugin;
    public static HashMap<UUID, GamePlayer> playerDataHandler;

    public PlayerController(Plugin plugin) {
        this.plugin = plugin;
        playerDataHandler = new HashMap<>();
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent playerLoginEvent) throws ClassNotFoundException, SQLException {
        Player p = playerLoginEvent.getPlayer();

        database.insert(p);
        GamePlayer g = database.select(p);
        playerDataHandler.put(g.getUUID(), g);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent playerQuitEvent) {
        Player p = playerQuitEvent.getPlayer();
        playerDataHandler.remove(p.getUniqueId());
    }
}
