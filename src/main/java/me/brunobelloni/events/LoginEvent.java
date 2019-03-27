package me.brunobelloni.events;

import me.brunobelloni.avl.AVLTree;
import me.sgray.template.spigotplugin.SpigotPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginEvent implements Listener {

    private final SpigotPlugin plugin;

    public AVLTree tree = new AVLTree();

    public LoginEvent(SpigotPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent playerLoginEvent) {
        Player p = playerLoginEvent.getPlayer();

        tree.root = tree.insert(tree.root, p.getUniqueId());

        tree.preOrder(tree.root);
    }

}
