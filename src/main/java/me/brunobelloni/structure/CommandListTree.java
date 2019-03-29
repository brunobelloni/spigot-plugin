package me.brunobelloni.structure;

import java.util.ArrayList;
import me.brunobelloni.enums.Messages;
import static me.brunobelloni.kits.AbstractKit.getChildren;
import me.brunobelloni.types.Gamer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class CommandListTree extends BukkitCommand {

    public CommandListTree(String name) {
        super(name);
        this.description = "Find the last time a player was online";
        this.usageMessage = "/seen <player>";
        this.setPermission("minesmash.commands.seen");
        this.setAliases(new ArrayList<String>());
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(Messages.DONT_HAVE_PERMISSION + "");
            return true;
        }

        DataStructure dataStructure = DataStructureHandler.dataStructure;

        dataStructure.listAbilitys();

        Gamer g = dataStructure.search((Player) sender);

        g.openInventoryMenu();
        
        System.out.println(getChildren());

        return true;
    }
}
