package me.brunobelloni.kits;

import me.brunobelloni.enums.Abilitys;
import me.brunobelloni.enums.Messages;
import me.brunobelloni.types.Gamer;
import org.bukkit.Sound;
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

        Gamer g = tree.search((Player) sender);

        g.clearInventory()
                .setAbility(Abilitys.PVP)
                .giveItem(diamondSword)
                .fillInventoryWithSoup()
                .playSound(Sound.NOTE_BASS_GUITAR)
                .sendMessage(Messages.CHOOSE_KIT + this.getLabel().toUpperCase());

        return true;
    }
}
