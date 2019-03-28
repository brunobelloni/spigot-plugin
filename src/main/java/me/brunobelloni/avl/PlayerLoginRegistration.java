package me.brunobelloni.avl;

import me.brunobelloni.Plugin;
import me.brunobelloni.types.Gamer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginRegistration implements Listener {

    private Plugin plugin;
    public static AVLTree tree = new AVLTree();

    public PlayerLoginRegistration(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent playerLoginEvent) {
        Player p = playerLoginEvent.getPlayer();
        Gamer g = new Gamer(p);        
        tree.insert(g);
    }
}
