package me.brunobelloni.listeners.player;

import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.game.GamePlayer;
import static me.brunobelloni.controllers.PlayerController.playerDataHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildEvent implements Listener {

    public HashMap<UUID, GamePlayer> playerData;

    public BuildEvent() {
        this.playerData = playerDataHandler;
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();

        if (!player.isOp()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();

        if (!player.isOp()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        event.setCancelled(true);
    }
}
