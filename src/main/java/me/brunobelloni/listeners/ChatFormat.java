package me.brunobelloni.listeners;

import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.game.GamePlayer;
import static me.brunobelloni.controllers.PlayerController.playerDataHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormat implements Listener {

    public HashMap<UUID, GamePlayer> playerData;

    public ChatFormat() {
        this.playerData = playerDataHandler;
    }

    @EventHandler
    public void chatFormat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        GamePlayer g = playerData.get(p.getUniqueId());
        event.setFormat(ChatColor.GRAY + p.getDisplayName() + ChatColor.RED + " » " + ChatColor.GRAY + event.getMessage());
    }
}
