package me.brunobelloni.api.kits;

import me.brunobelloni.enums.Messages;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.brunobelloni.controllers.AbilityController.putAbility;
import static me.brunobelloni.controllers.PlayerController.fillInventoryWithSoup;
import static me.brunobelloni.enums.Abilitys.PVP;
import static me.brunobelloni.enums.Item.DIAMOND_SWORD;
import static me.brunobelloni.enums.MenuItem.DONT_HAS_PVP;
import static me.brunobelloni.enums.MenuItem.HAS_PVP;

public class Pvp extends KitAPI {

    public Pvp(String name) {
        super(name);
        setItemMenu(HAS_PVP);
        setDontItemMenu(DONT_HAS_PVP);
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (!commandSenderIsPlayer(sender)) return false;
        if (!playerHasPermission(sender)) return false;

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
