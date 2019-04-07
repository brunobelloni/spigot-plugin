package me.brunobelloni.listeners.pvp;

import static me.brunobelloni.enums.CustomItem.BOWL;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Soup implements Listener {

    public Soup() {
    }

    @EventHandler
    public void soup(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (p.getItemInHand().getType().equals(Material.MUSHROOM_SOUP)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (p.getHealth() != 20.0D && p.getHealth() + 1.0D <= 20.0D) {
                    e.setCancelled(true);
                    p.setHealth(p.getHealth() + 7.0D > p.getMaxHealth() ? p.getMaxHealth() : p.getHealth() + 7.0D);
                    p.playSound(p.getLocation(), Sound.EAT, 1.0F, 1.0F);
                    p.setItemInHand(BOWL.getItem());
                }
            }
        }
    }
}
