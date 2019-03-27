package me.brunobelloni.commands;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
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
            sender.sendMessage(ChatColor.RED + "You don't have permission.");
            return true;
        }
       
        if (args.length != 1) {
            return true;
        }
       
        OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
        if (p.isOnline()) {
            sender.sendMessage(ChatColor.GRAY + args[0] + " is " + ChatColor.GREEN + "online");
            return true;
        } else {
            sender.sendMessage(ChatColor.GRAY + args[0] + " is " + ChatColor.RED + "offline");
            return true;
        }
    }
    
}
