package me.brunobelloni.api.kits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.Plugin;
import me.brunobelloni.api.chest.ChestAPI.onClick;
import static me.brunobelloni.api.chest.KitMenu.addButtonInOrder;
import static me.brunobelloni.controllers.PlayerController.onlinePlayersController;
import me.brunobelloni.game.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class KitAPI extends BukkitCommand implements Listener {

    protected Plugin plugin;
    protected HashMap<UUID, GamePlayer> onlinePlayers;

    public KitAPI(final String name, ItemStack item) {
        super(name);
        this.plugin = (Plugin) Bukkit.getPluginManager().getPlugins()[0];
        this.description = "Escolha o kit " + name;
        this.usageMessage = "/" + name;
        this.setPermission("kit." + name);
        this.setAliases(new ArrayList<String>());
        this.onlinePlayers = onlinePlayersController;

        addButtonInOrder(item, new onClick() {
            @Override
            public boolean click(Player clicker) {
                clicker.performCommand(name);
                return true;
            }
        });
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}
