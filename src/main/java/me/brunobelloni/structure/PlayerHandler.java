package me.brunobelloni.structure;

import me.brunobelloni.Plugin;
import me.brunobelloni.types.Gamer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerHandler implements Listener {

    private Plugin plugin;
    public static DataStructure dataStructure;

    public PlayerHandler(Plugin plugin) {
        this.plugin = plugin;
        this.dataStructure = new DataStructure();
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent playerLoginEvent) {
        Player p = playerLoginEvent.getPlayer();
        Gamer g = new Gamer(p);
        dataStructure.add(g);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent playerQuitEvent) {
        Player p = playerQuitEvent.getPlayer();
        Gamer g = new Gamer(p);
        dataStructure.remove(g);
    }
}
