package me.brunobelloni.kits;

import java.util.HashSet;
import me.brunobelloni.Plugin;
import me.brunobelloni.enums.Abilitys;
import static me.brunobelloni.enums.CustomItem.THOR_ITEM;
import me.brunobelloni.enums.Messages;
import me.brunobelloni.types.Gamer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Thor extends AbstractKit {

    private Plugin plugin;
    private ItemStack thorItem;

    public Thor(Plugin plugin, String name) {
        super(name);
        this.plugin = plugin;
        this.thorItem = THOR_ITEM.getItem();
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(Messages.DONT_HAVE_PERMISSION + "");
            return true;
        }

        Gamer g = tree.search((Player) sender);

        g.clearInventory()
                .setAbility(Abilitys.THOR)
                .giveItem(diamondSword)
                .giveItem(thorItem)
                .fillInventoryWithSoup()
                .playSound(Sound.NOTE_BASS_GUITAR)
                .sendMessage(Messages.CHOOSE_KIT + this.getLabel().toUpperCase());

        return true;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerThor(PlayerInteractEvent e) {
        if (e.getPlayer() instanceof Player) {
            Player p = e.getPlayer();
            final Gamer g = tree.search(p);

            if (g.getAbility() == Abilitys.THOR) {
                if ((e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && (p.getItemInHand() == THOR_ITEM.getItem())) {

                    if (g.isOnCooldown()) {
                        g.sendMessage(Messages.COOLDOWN_WARNING + "");
                    } else {
                        HashSet<Material> transparent = new HashSet<>();
                        transparent.add(Material.AIR);
                        Block block = e.getPlayer().getTargetBlock(transparent, 120);
                        e.getPlayer().getWorld().strikeLightning(block.getLocation());

                        g.putCooldown();

                        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                            public void run() {
                                g.removeCooldown();
                            }
                        }, 20 * 5);
                    }
                }
            }
        }
    }
}
