package me.brunobelloni.events;

import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.Plugin;
import static me.brunobelloni.structure.HashHandler.playerDataHandler;
import me.brunobelloni.types.Gamer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    private Plugin plugin;
    public HashMap<UUID, Gamer> playerData;

    public JoinEvent(Plugin plugin) {
        this.plugin = plugin;
        this.playerData = playerDataHandler;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Gamer g = playerData.get(p.getUniqueId());
        g.clearInventory();
        g.giveMenuItens();
    }
}
