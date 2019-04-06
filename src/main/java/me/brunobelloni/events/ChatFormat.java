package me.brunobelloni.events;

import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.Plugin;
import me.brunobelloni.types.Gamer;
import static me.brunobelloni.types.HashHandler.playerDataHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormat implements Listener {

    private Plugin plugin;
    public HashMap<UUID, Gamer> playerData;

    public ChatFormat(Plugin plugin) {
        this.plugin = plugin;
        this.playerData = playerDataHandler;
    }

    @EventHandler
    public void chatFormat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        Gamer g = playerData.get(p.getUniqueId());
        event.setFormat(ChatColor.GRAY + p.getDisplayName() + ChatColor.RED + " » " + ChatColor.GRAY + event.getMessage());
    }
}
