package me.brunobelloni.listeners.player;

import static me.brunobelloni.game.GamePlayer.giveMenuItens;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinServer implements Listener {

    public JoinServer() {
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player p = e.getPlayer();
        p.getInventory().clear();
        giveMenuItens(p);
    }
}
