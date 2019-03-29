package me.brunobelloni.events;

import me.brunobelloni.Plugin;
import me.brunobelloni.structure.DataStructure;
import static me.brunobelloni.structure.DataStructureHandler.dataStructure;
import me.brunobelloni.types.Gamer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {

    private Plugin plugin;
    public DataStructure tree;

    public DeathEvent(Plugin plugin) {
        this.plugin = plugin;
        this.tree = dataStructure;
    }

    public void onDeath(PlayerDeathEvent playerDeathEvent) {
        Player p = playerDeathEvent.getEntity();
        Gamer g = dataStructure.search(p);
        g.removeAbility();
    }

}
