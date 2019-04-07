package me.brunobelloni.listeners.player;

import java.util.HashMap;
import java.util.UUID;
import static me.brunobelloni.controllers.PlayerController.onlinePlayersController;
import me.brunobelloni.game.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinServer implements Listener {

    public HashMap<UUID, GamePlayer> onlinePlayers;

    public JoinServer() {
        this.onlinePlayers = onlinePlayersController;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player p = e.getPlayer();
        GamePlayer gp = onlinePlayers.get(p.getUniqueId());
        gp.clearInventory();
        gp.giveMenuItens();
    }
}
