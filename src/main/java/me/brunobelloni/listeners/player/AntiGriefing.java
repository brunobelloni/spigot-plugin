package me.brunobelloni.listeners.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class AntiGriefing implements Listener {

    public AntiGriefing() {
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
    public void onBlockBurn(BlockBurnEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent e) {
        e.setCancelled(true);
    }
}
