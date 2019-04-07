package me.brunobelloni.api.kits;

import java.util.HashSet;
import me.brunobelloni.enums.Abilitys;
import static me.brunobelloni.enums.Cooldown.THOR_COOLDOWN;
import static me.brunobelloni.enums.CustomItem.DIAMOND_SWORD;
import static me.brunobelloni.enums.CustomItem.THOR_ITEM;
import me.brunobelloni.enums.Messages;
import static me.brunobelloni.enums.Messages.COMMAND_FROM_CONSOLE;
import static me.brunobelloni.enums.Messages.COOLDOWN_WARNING_AFTER;
import static me.brunobelloni.enums.Messages.COOLDOWN_WARNING_BEFORE;
import static me.brunobelloni.enums.Messages.DONT_HAVE_PERMISSION;
import me.brunobelloni.game.GamePlayer;
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

    private ItemStack thorItem;
    private Integer cooldown;

    public Thor(String name) {
        super(name, DIAMOND_SWORD.getItem());
        this.cooldown = THOR_COOLDOWN;
        this.thorItem = THOR_ITEM.getItem();
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
        GamePlayer g = onlinePlayers.get(p.getUniqueId());

        g.clearInventory()
                .setAbility(Abilitys.THOR)
                .giveItem(DIAMOND_SWORD.getItem())
                .giveItem(thorItem)
                .fillInventoryWithSoup()
                .playSound(Sound.NOTE_BASS_GUITAR)
                .sendMessage(Messages.CHOOSE_KIT + this.getLabel().toUpperCase());

        return true;
    }

    @EventHandler()
    public void onThor(PlayerInteractEvent e) {
        if (e.getPlayer() instanceof Player) {
            Player p = e.getPlayer();
            final GamePlayer g = onlinePlayers.get(p.getUniqueId());
            if (g.getAbility() == Abilitys.THOR && p.getItemInHand().equals(THOR_ITEM.getItem())) {
                if ((e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                    long actualTime = System.nanoTime();
                    if (g.isOnCooldown()) {
                        double d = (actualTime - g.getCooldown()) / 1e9;
                        int diff = this.cooldown - (int) d;
                        g.sendMessage(COOLDOWN_WARNING_BEFORE + diff + COOLDOWN_WARNING_AFTER);
                    } else {
                        HashSet<Material> transparent = new HashSet<>();
                        transparent.add(Material.AIR);
                        Block block = e.getPlayer().getTargetBlock(transparent, 120);
                        e.getPlayer().getWorld().strikeLightning(block.getLocation());
                        g.putCooldown(actualTime);

                        new CooldownAPI(g).runTaskLaterAsynchronously(super.plugin, this.cooldown * 20);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onThorLightning(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            GamePlayer g = onlinePlayers.get(p.getUniqueId());
            if (g.getAbility() == Abilitys.THOR && e.getCause().equals(DamageCause.LIGHTNING)) {
                e.setCancelled(true);
            }
        }
    }
}
