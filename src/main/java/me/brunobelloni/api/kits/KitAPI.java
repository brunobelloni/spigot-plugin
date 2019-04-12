package me.brunobelloni.api.kits;

import java.util.ArrayList;
import me.brunobelloni.Plugin;
import static me.brunobelloni.controllers.KitMenuController.addKitList;
import org.bukkit.Bukkit;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class KitAPI extends BukkitCommand implements Listener {

    private String name;
    protected Plugin plugin;

    public KitAPI(final String name, ItemStack item) {
        super(name);
        this.name = name;
        this.plugin = (Plugin) Bukkit.getPluginManager().getPlugins()[0];
        this.description = "Escolha o kit " + name;
        this.usageMessage = "/" + name;
        this.setPermission("kit." + name);
        this.setAliases(new ArrayList<String>());

        addKitList(new ItemMenu(item, name, getPermission()));
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}
