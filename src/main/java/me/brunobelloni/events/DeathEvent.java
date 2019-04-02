package me.brunobelloni.events;

import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.Plugin;
import static me.brunobelloni.types.HashHandler.playerDataHandler;
import me.brunobelloni.types.Gamer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {

    private Plugin plugin;
    public HashMap<UUID, Gamer> playerData;

    public DeathEvent(Plugin plugin) {
        this.plugin = plugin;
        this.playerData = playerDataHandler;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.setDeathMessage(null);
        
        Player p = e.getEntity();
        Gamer g = playerData.get(p.getUniqueId());
        g.removeAbility();
        g.sendMessage("Você morreu!");
    }

}
