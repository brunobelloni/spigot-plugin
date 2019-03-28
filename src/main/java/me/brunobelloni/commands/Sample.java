package me.brunobelloni.commands;

import java.util.ArrayList;
import me.brunobelloni.avl.AVLTree;
import me.brunobelloni.enums.Messages;
import me.brunobelloni.avl.PlayerLoginRegistration;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

public class Sample extends BukkitCommand {

    public Sample(String name) {
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
        
        AVLTree avlTree = PlayerLoginRegistration.tree;
        
        avlTree.preOrder(avlTree.root);

        return true;
    }
}
