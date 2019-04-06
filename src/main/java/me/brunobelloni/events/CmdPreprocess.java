package me.brunobelloni.events;

import me.brunobelloni.Plugin;
import static me.brunobelloni.enums.Messages.DONT_HAVE_PERMISSION;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CmdPreprocess implements Listener {

    private Plugin plugin;

    public CmdPreprocess(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
        String cmd = e.getMessage().toLowerCase();

        if ((cmd.startsWith("/me ") || cmd.startsWith("/?")
                || cmd.startsWith("/about") || cmd.startsWith("/w")
                || cmd.startsWith("/ver") || cmd.startsWith("/version")
                || cmd.startsWith("/bukkit:me") || cmd.startsWith("/bukkit:help")
                || cmd.startsWith("/bukkit:?") || cmd.startsWith("/bukkit:about")
                || cmd.startsWith("/bukkit:w") || cmd.startsWith("/bukkit:version")
                || cmd.startsWith("/bukkit:ver")) && !e.getPlayer().isOp()) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(DONT_HAVE_PERMISSION);
        }
    }
}
