package me.brunobelloni.listeners.player;

import java.sql.SQLException;
import static me.brunobelloni.controllers.MenuController.addMenuOnPlayerLogin;
import static me.brunobelloni.controllers.PlayerController.asyncInsertPlayer;
import static me.brunobelloni.controllers.PlayerController.giveMenuItens;
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

        addMenuOnPlayerLogin(p, false);

        try {
            asyncInsertPlayer(p);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        p.getInventory().clear();
        giveMenuItens(p);
    }
}
