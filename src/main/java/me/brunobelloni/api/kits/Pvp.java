package me.brunobelloni.api.kits;

import static me.brunobelloni.controllers.AbilityController.putAbility;
import static me.brunobelloni.controllers.PlayerController.fillInventoryWithSoup;
import static me.brunobelloni.enums.Abilitys.PVP;
import static me.brunobelloni.enums.CustomItem.DIAMOND_SWORD;
import static me.brunobelloni.enums.CustomMenuItem.DONT_HAS_PVP;
import static me.brunobelloni.enums.CustomMenuItem.HAS_PVP;
import me.brunobelloni.enums.Messages;
import static me.brunobelloni.enums.Messages.COMMAND_FROM_CONSOLE;
import static me.brunobelloni.enums.Messages.DONT_HAVE_PERMISSION;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Pvp extends KitAPI {

    public Pvp(String name) {
        super(name);
        setItemMenu(HAS_PVP);
        setDontItemMenu(DONT_HAS_PVP);
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

        p.getInventory().clear();
        putAbility(p, PVP);
        p.getInventory().addItem(DIAMOND_SWORD);
        fillInventoryWithSoup(p);
        p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1.0F, 1.0F);
        p.sendMessage(Messages.CHOOSE_KIT + this.getLabel().toUpperCase());

        return true;
    }
}
