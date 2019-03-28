package me.brunobelloni.kits;

import me.brunobelloni.enums.Messages;
import org.bukkit.command.CommandSender;

public class Pvp extends AbstractKit {

    public Pvp(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(Messages.DONT_HAVE_PERMISSION + "");
            return true;
        }

        

        sender.sendMessage("Você escolheu o kit Pvp");
        return true;
    }
}
