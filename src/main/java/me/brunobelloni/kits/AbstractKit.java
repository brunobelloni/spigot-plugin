package me.brunobelloni.kits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import static me.brunobelloni.enums.CustomItem.DIAMOND_SWORD;
import static me.brunobelloni.structure.HashHandler.playerDataHandler;
import me.brunobelloni.types.Gamer;
import org.bukkit.Bukkit;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractKit extends BukkitCommand implements Listener {

    protected HashMap<UUID, Gamer> playerData;
    protected ItemStack diamondSword;

    public AbstractKit(String name) {
        super(name);
        this.description = "Escolha o kit " + name;
        this.usageMessage = "/" + name;
        this.setPermission("kit." + name);
        this.setAliases(new ArrayList<String>());
        this.playerData = playerDataHandler;

        this.diamondSword = DIAMOND_SWORD.getItem();
        Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().getPlugins()[0]);
    }
}
