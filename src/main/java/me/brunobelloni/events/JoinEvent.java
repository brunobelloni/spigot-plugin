package me.brunobelloni.events;

import me.brunobelloni.Plugin;
import me.brunobelloni.structure.DataStructure;
import static me.brunobelloni.structure.DataStructureHandler.dataStructure;
import me.brunobelloni.types.Gamer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    private Plugin plugin;
    public DataStructure tree;

    public JoinEvent(Plugin plugin) {
        this.plugin = plugin;
        this.tree = dataStructure;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent playerLoginEvent) {
        Player p = playerLoginEvent.getPlayer();
        Gamer g = dataStructure.search(p);
        g.clearInventory();
        g.giveMenuItens();
    }
}
