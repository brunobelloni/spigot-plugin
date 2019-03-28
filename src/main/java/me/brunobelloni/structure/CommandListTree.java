package me.brunobelloni.structure;

import java.util.ArrayList;
import java.util.List;
import me.brunobelloni.enums.Messages;
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

        DataStructure dataStructure = PlayerHandler.dataStructure;
        List<Gamer> players = new ArrayList<>();

        Gamer g = new Gamer((Player) sender);

        dataStructure.list();

        if (dataStructure.search(g) != null) {
            System.out.println("Encontrou " + g.getPlayer().getName());
            System.out.println(dataStructure.search(g));
        }

        return true;
    }
}
