package me.brunobelloni.listeners;

import me.brunobelloni.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemDrop implements Listener {

    private Plugin plugin;

    public ItemDrop() {
        this.plugin = (Plugin) Bukkit.getPluginManager().getPlugins()[0];
    }

    @EventHandler
    public void itemDrop(final ItemSpawnEvent e) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Item item = e.getEntity();
                Location loc = item.getLocation();
                World world = loc.getWorld();

                item.remove();
                world.playEffect(loc, Effect.SMOKE, 10);
                world.playSound(loc, Sound.LAVA_POP, 1.0F, 1.0F);
            }
        }.runTaskLater(plugin, 5 * 20);
    }

    @EventHandler
    public void onPlayerDropSword(PlayerDropItemEvent e) {
        Material itemDropped = e.getItemDrop().getItemStack().getType();

        if (itemDropped.equals(Material.DIAMOND_SWORD)
                || itemDropped.equals(Material.IRON_SWORD)
                || itemDropped.equals(Material.GOLD_SWORD)
                || itemDropped.equals(Material.BOW)
                || itemDropped.equals(Material.ARROW)
                || itemDropped.equals(Material.FIREWORK)
                || itemDropped.equals(Material.FEATHER)
                || itemDropped.equals(Material.REDSTONE_TORCH_ON)
                || itemDropped.equals(Material.PORTAL)
                || itemDropped.equals(Material.FISHING_ROD)
                || itemDropped.equals(Material.WOOD_AXE)
                || itemDropped.equals(Material.SNOW_BALL)
                || itemDropped.equals(Material.INK_SACK)
                || itemDropped.equals(Material.SADDLE)
                || itemDropped.equals(Material.ENDER_PEARL)
                || itemDropped.equals(Material.BLAZE_ROD)
                || itemDropped.equals(Material.SUGAR)
                || itemDropped.equals(Material.REDSTONE)
                || itemDropped.equals(Material.IRON_FENCE)
                || itemDropped.equals(Material.COOKIE)
                || itemDropped.equals(Material.WATCH)
                || itemDropped.equals(Material.DIAMOND)
                || itemDropped.equals(Material.GOLD_INGOT)
                || itemDropped.equals(Material.LADDER)
                || itemDropped.equals(Material.GLOWSTONE_DUST)
                || itemDropped.equals(Material.SUGAR)
                || itemDropped.equals(Material.RECORD_9)
                || itemDropped.equals(Material.NETHER_BRICK_ITEM)
                || itemDropped.equals(Material.ENDER_PORTAL_FRAME)
                || itemDropped.equals(Material.BOOK)
                || itemDropped.equals(Material.STRING)
                || itemDropped.equals(Material.MILK_BUCKET)
                || itemDropped.equals(Material.LEASH)
                || itemDropped.equals(Material.POTION)
                || itemDropped.equals(Material.IRON_AXE)
                || itemDropped.equals(Material.NETHER_STAR)
                || itemDropped.equals(Material.STICK)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void PegarItens(PlayerPickupItemEvent e) {
        Material itemPick = e.getItem().getItemStack().getType();

        if (!(itemPick.equals(Material.MUSHROOM_SOUP)
                || itemPick.equals(Material.WOOD_SWORD)
                || itemPick.equals(Material.STONE_SWORD)
                || itemPick.equals(Material.LEATHER_HELMET)
                || itemPick.equals(Material.LEATHER_CHESTPLATE)
                || itemPick.equals(Material.LEATHER_LEGGINGS)
                || itemPick.equals(Material.LEATHER_BOOTS)
                || itemPick.equals(Material.RED_MUSHROOM)
                || itemPick.equals(Material.BROWN_MUSHROOM)
                || itemPick.equals(Material.BOWL))) {
            e.setCancelled(true);
        }
    }
}
