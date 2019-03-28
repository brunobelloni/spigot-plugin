package me.brunobelloni.kits;

import java.util.ArrayList;
import me.brunobelloni.enums.CustomItem;
import me.brunobelloni.structure.DataStructure;
import me.brunobelloni.structure.DataStructureHandler;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractKit extends BukkitCommand implements Listener {

    protected DataStructure dataStructure;
    protected ItemStack diamondSword;

    public AbstractKit(String name) {
        super(name);
        this.description = "Escolha o kit " + name;
        this.usageMessage = "/" + name;
        this.setPermission("kit." + name);
        this.setAliases(new ArrayList<String>());

        this.dataStructure = DataStructureHandler.dataStructure;
        this.diamondSword = CustomItem.DIAMOND_SWORD.getItem();
    }
}
