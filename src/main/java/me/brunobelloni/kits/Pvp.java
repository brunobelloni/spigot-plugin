package me.brunobelloni.kits;

import me.brunobelloni.enums.Abilitys;
import me.brunobelloni.enums.Messages;
import me.brunobelloni.types.Gamer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

        Gamer g = dataStructure.search(new Gamer((Player) sender));
        g.setAbility(Abilitys.PVP);

        sender.sendMessage("Você escolheu o kit Pvp");

        return true;
    }
}
