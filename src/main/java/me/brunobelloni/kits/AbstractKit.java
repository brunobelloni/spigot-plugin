package me.brunobelloni.kits;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import me.brunobelloni.Plugin;
import static me.brunobelloni.enums.CustomItem.DIAMOND_SWORD;
import me.brunobelloni.structure.DataStructure;
import static me.brunobelloni.structure.DataStructureHandler.dataStructure;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractKit extends BukkitCommand implements Listener {

    protected Plugin plugin;
    protected DataStructure tree;
    protected ItemStack diamondSword;
    private static Set children = new HashSet();

    public AbstractKit(String name) {
        super(name);
        this.description = "Escolha o kit " + name;
        this.usageMessage = "/" + name;
        this.setPermission("kit." + name);
        this.setAliases(new ArrayList<String>());
        this.tree = dataStructure;
        this.diamondSword = DIAMOND_SWORD.getItem();

        synchronized (this) {
            children.add(this);
        }
    }

    public static Set getChildren() {
        return children;
    }
}
