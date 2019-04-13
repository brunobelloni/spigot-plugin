package me.brunobelloni.api.kits;

import static me.brunobelloni.controllers.AbilityController.getAbility;
import static me.brunobelloni.controllers.AbilityController.putAbility;
import static me.brunobelloni.controllers.CooldownController.getCooldown;
import static me.brunobelloni.controllers.CooldownController.isOnCooldown;
import static me.brunobelloni.controllers.CooldownController.putCooldown;
import static me.brunobelloni.controllers.CooldownController.removeCooldown;
import static me.brunobelloni.controllers.PlayerController.fillInventoryWithSoup;
import static me.brunobelloni.enums.Abilitys.SWITCHER;
import static me.brunobelloni.enums.Cooldown.SWITCHER_COOLDOWN;
import static me.brunobelloni.enums.Item.IRON_SWORD;
import static me.brunobelloni.enums.Item.SWITCHER_CD;
import static me.brunobelloni.enums.Item.SWITCHER_ITEM;
import static me.brunobelloni.enums.MenuItem.DONT_HAS_SWITCHER;
import static me.brunobelloni.enums.MenuItem.HAS_SWITCHER;
import me.brunobelloni.enums.Messages;
import static me.brunobelloni.enums.Messages.COMMAND_FROM_CONSOLE;
import static me.brunobelloni.enums.Messages.COOLDOWN_WARNING_AFTER;
import static me.brunobelloni.enums.Messages.COOLDOWN_WARNING_BEFORE;
import static me.brunobelloni.enums.Messages.DONT_HAVE_PERMISSION;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Switcher extends KitAPI {

    public Switcher(String name) {
        super(name);
        setItemMenu(HAS_SWITCHER);
        setDontItemMenu(DONT_HAS_SWITCHER);
        setKitCooldown(SWITCHER_COOLDOWN);
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
        putAbility(p, SWITCHER);
        p.getInventory().addItem(IRON_SWORD);
        p.getInventory().addItem(SWITCHER_ITEM);
        fillInventoryWithSoup(p);
        p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1.0F, 1.0F);
        p.sendMessage(Messages.CHOOSE_KIT + this.getLabel().toUpperCase());

        return true;
    }

    @EventHandler
    public void Switcher(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Snowball) {
            if (((Snowball) e.getDamager()).getShooter() instanceof Player) {
                final Player shooter = (Player) ((Snowball) e.getDamager()).getShooter();
                Player hitted = (Player) e.getEntity();

                if (getAbility(shooter).equals(SWITCHER)) {
                    long actualTime = System.nanoTime();
                    if (isOnCooldown(shooter)) {
                        double d = (actualTime - getCooldown(shooter)) / 1e9;
                        int diff = super.getKitCooldown() - (int) d;
                        shooter.sendMessage(COOLDOWN_WARNING_BEFORE + diff + COOLDOWN_WARNING_AFTER);
                    } else {
                        hitted.teleport(shooter.getLocation());
                        shooter.teleport(hitted.getLocation());
                        hitted.playSound(hitted.getLocation(), Sound.ENDERMAN_TELEPORT, 5.0F, 1.0F);
                        shooter.playSound(shooter.getLocation(), Sound.ENDERMAN_TELEPORT, 5.0F, 1.0F);
                        putCooldown(shooter, actualTime);
                        shooter.getInventory().addItem(SWITCHER_CD);

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                removeCooldown(shooter);
                                shooter.getInventory().remove(SWITCHER_CD);
                                shooter.getInventory().addItem(SWITCHER_ITEM);
                            }
                        }.runTaskLaterAsynchronously(super.getInstance(), super.getKitCooldown() * 20);
                    }
                }
            }
        }
    }
}
