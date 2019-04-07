package me.brunobelloni.kits;

import me.brunobelloni.enums.Abilitys;
import me.brunobelloni.enums.Messages;
import static me.brunobelloni.enums.Messages.COMMAND_FROM_CONSOLE;
import static me.brunobelloni.enums.Messages.DONT_HAVE_PERMISSION;
import me.brunobelloni.game.GamePlayer;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Pvp extends AbstractKit {

    public Pvp(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(COMMAND_FROM_CONSOLE);
        }

        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(DONT_HAVE_PERMISSION);
            return true;
        }

        Player p = (Player) sender;
        GamePlayer g = playerData.get(p.getUniqueId());

        g.clearInventory()
                .setAbility(Abilitys.PVP)
                .giveItem(diamondSword)
                .fillInventoryWithSoup()
                .playSound(Sound.NOTE_BASS_GUITAR)
                .sendMessage(Messages.CHOOSE_KIT + this.getLabel().toUpperCase());

        return true;
    }
}
