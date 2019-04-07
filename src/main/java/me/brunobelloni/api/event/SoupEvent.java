package me.brunobelloni.api.event;

import me.brunobelloni.game.GamePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;

public class SoupEvent extends Event {

    private final GamePlayer gp;
    private final PlayerInteractEvent event;
    private static final HandlerList handlers = new HandlerList();

    public SoupEvent(PlayerInteractEvent event, GamePlayer gp) {
        this.event = event;
        this.gp = gp;
    }

    public GamePlayer getGamePlayer() {
        return gp;
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
