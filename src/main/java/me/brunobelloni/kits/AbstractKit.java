package me.brunobelloni.kits;

import java.util.ArrayList;
import me.brunobelloni.structure.DataStructure;
import me.brunobelloni.structure.PlayerHandler;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.event.Listener;

public abstract class AbstractKit extends BukkitCommand implements Listener {

    protected DataStructure dataStructure = PlayerHandler.dataStructure;

    public AbstractKit(String name) {
        super(name);
        this.description = "Escolha o kit " + name;
        this.usageMessage = "/" + name;
        this.setPermission("kit." + name);
        this.setAliases(new ArrayList<String>());
    }
}
