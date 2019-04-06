package me.brunobelloni.types;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.Plugin;
import static me.brunobelloni.Plugin.database;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class HashHandler implements Listener {

    private Plugin plugin;
    public static HashMap<UUID, Gamer> playerDataHandler;

    public HashHandler(Plugin plugin) {
        this.plugin = plugin;
        playerDataHandler = new HashMap<>();
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent playerLoginEvent) throws ClassNotFoundException, SQLException {
        Player p = playerLoginEvent.getPlayer();
        Gamer g = new Gamer(p);
        playerDataHandler.put(g.getUUID(), g);

        database.insert(g.getUUID());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent playerQuitEvent) {
        Player p = playerQuitEvent.getPlayer();
        playerDataHandler.remove(p.getUniqueId());
    }
}
