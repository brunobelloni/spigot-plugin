package me.brunobelloni.listeners.player;

import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.game.GamePlayer;
import static me.brunobelloni.controllers.PlayerController.playerDataHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    public HashMap<UUID, GamePlayer> playerData;

    public JoinEvent() {
        this.playerData = playerDataHandler;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player p = e.getPlayer();
        GamePlayer g = playerData.get(p.getUniqueId());
        g.clearInventory();
        g.giveMenuItens();
    }
}
