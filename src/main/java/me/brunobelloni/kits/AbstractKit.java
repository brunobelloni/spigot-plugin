package me.brunobelloni.kits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.Plugin;
import static me.brunobelloni.enums.CustomItem.DIAMOND_SWORD;
import me.brunobelloni.game.GamePlayer;
import static me.brunobelloni.controllers.PlayerController.playerDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractKit extends BukkitCommand implements Listener {

    protected Plugin plugin = (Plugin) Bukkit.getPluginManager().getPlugins()[0];
    protected HashMap<UUID, GamePlayer> playerData;
    protected ItemStack diamondSword;

    public AbstractKit(String name) {
        super(name);
        this.description = "Escolha o kit " + name;
        this.usageMessage = "/" + name;
        this.setPermission("kit." + name);
        this.setAliases(new ArrayList<String>());
        this.playerData = playerDataHandler;

        this.diamondSword = DIAMOND_SWORD.getItem();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}
