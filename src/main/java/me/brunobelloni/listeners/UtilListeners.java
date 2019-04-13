package me.brunobelloni.listeners;

import static me.brunobelloni.controllers.MenuController.openKitMenu;
import static me.brunobelloni.enums.Item.KIT_ITEM;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

public class UtilListeners implements Listener {

    public UtilListeners() {
    }

    @EventHandler
    public void neverRain(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void foodLevelChange(FoodLevelChangeEvent e) {
        e.setFoodLevel(20);
    }

    @EventHandler
    public void creatureSpawn(CreatureSpawnEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void clickChest(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action action = e.getAction();
        ItemStack item = p.getItemInHand();

        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (item.equals(KIT_ITEM)) {
                e.setCancelled(true);
                openKitMenu(p);
                p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1.0F, 1.0F);
            }
        }
    }
}
