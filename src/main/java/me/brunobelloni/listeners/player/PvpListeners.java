package me.brunobelloni.listeners.player;

import me.brunobelloni.api.event.SoupEvent;
import static me.brunobelloni.enums.Item.BOWL;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PvpListeners implements Listener {

    public PvpListeners() {
    }

    @EventHandler
    public void soupUseEvent(SoupEvent e) {
        Player p = e.getPlayer();
        p.setHealth(p.getHealth() + 7.0D > p.getMaxHealth() ? p.getMaxHealth() : p.getHealth() + 7.0D);
        p.playSound(p.getLocation(), Sound.EAT, 1.0F, 1.0F);
        p.setItemInHand(BOWL);
    }
}
