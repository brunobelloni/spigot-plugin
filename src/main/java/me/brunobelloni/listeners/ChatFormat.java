package me.brunobelloni.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormat implements Listener {

    public ChatFormat() {
    }

    @EventHandler
    public void chatFormat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        e.setFormat(ChatColor.GRAY + p.getDisplayName() + ChatColor.RED + " » " + ChatColor.GRAY + e.getMessage());
    }
}
