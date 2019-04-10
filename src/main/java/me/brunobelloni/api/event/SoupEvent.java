package me.brunobelloni.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;

public class SoupEvent extends Event {

    private final Player p;
    private final PlayerInteractEvent event;
    private static final HandlerList handlers = new HandlerList();

    public SoupEvent(PlayerInteractEvent event, Player p) {
        this.event = event;
        this.p = p;
    }

    public Player getPlayer() {
        return p;
    }

    public PlayerInteractEvent getEvent() {
        return event;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
