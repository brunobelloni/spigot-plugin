package me.brunobelloni.api.kits;

import java.util.HashSet;
import static me.brunobelloni.controllers.AbilityController.getAbility;
import static me.brunobelloni.controllers.AbilityController.putAbility;
import static me.brunobelloni.controllers.CooldownController.getCooldown;
import static me.brunobelloni.controllers.CooldownController.isOnCooldown;
import static me.brunobelloni.controllers.CooldownController.putCooldown;
import static me.brunobelloni.controllers.PlayerController.fillInventoryWithSoup;
import me.brunobelloni.enums.Abilitys;
import static me.brunobelloni.enums.Abilitys.THOR;
import static me.brunobelloni.enums.Cooldown.THOR_COOLDOWN;
import static me.brunobelloni.enums.Item.IRON_SWORD;
import static me.brunobelloni.enums.Item.THOR_ITEM;
import static me.brunobelloni.enums.MenuItem.DONT_HAS_THOR;
import static me.brunobelloni.enums.MenuItem.HAS_THOR;
import me.brunobelloni.enums.Messages;
import static me.brunobelloni.enums.Messages.COMMAND_FROM_CONSOLE;
import static me.brunobelloni.enums.Messages.COOLDOWN_WARNING_AFTER;
import static me.brunobelloni.enums.Messages.COOLDOWN_WARNING_BEFORE;
import static me.brunobelloni.enums.Messages.DONT_HAVE_PERMISSION;
import me.brunobelloni.schedualar.CooldownAPI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Thor extends KitAPI {

    private final ItemStack thorItem;

    public Thor(String name) {
        super(name);
        setItemMenu(HAS_THOR);
        setDontItemMenu(DONT_HAS_THOR);
        setKitCooldown(THOR_COOLDOWN);
        this.thorItem = THOR_ITEM;
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (!commandSenderIsPlayer(sender)) return false;
        if (!playerHasPermission(sender)) return false;

        Player p = (Player) sender;

        p.getInventory().clear();
        putAbility(p, THOR);
        p.getInventory().addItem(IRON_SWORD);
        p.getInventory().addItem(thorItem);
        fillInventoryWithSoup(p);
        p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1.0F, 1.0F);
        p.sendMessage(Messages.CHOOSE_KIT + this.getLabel().toUpperCase());

        return true;
    }

    @EventHandler()
    public void onThor(PlayerInteractEvent e) {
        if (e.getPlayer() instanceof Player) {
            Player p = e.getPlayer();
            if ((e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                if (getAbility(p) == Abilitys.THOR && p.getItemInHand().equals(thorItem)) {
                    long actualTime = System.nanoTime();

                    if (isOnCooldown(p)) {
                        double d = (actualTime - getCooldown(p)) / 1e9;
                        int diff = super.getKitCooldown() - (int) d;
                        p.sendMessage(COOLDOWN_WARNING_BEFORE + diff + COOLDOWN_WARNING_AFTER);
                    } else {
                        HashSet<Material> transparent = new HashSet<>();
                        transparent.add(Material.AIR);
                        Block block = e.getPlayer().getTargetBlock(transparent, 120);
                        e.getPlayer().getWorld().strikeLightning(block.getLocation());
                        putCooldown(p, actualTime);
                        new CooldownAPI(p).runTaskLaterAsynchronously(super.getInstance(), super.getKitCooldown() * 20);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onThorLightning(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (getAbility(p) == Abilitys.THOR && e.getCause().equals(DamageCause.LIGHTNING)) {
                e.setCancelled(true);
            }
        }
    }
}
