package me.brunobelloni.api.chest;

import me.brunobelloni.api.chest.ChestAPI.onClick;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class KitMenu implements Listener {

    private static Integer actualSlot = 0;
    private static ChestAPI menu = new ChestAPI("IconMenu", 2);

    public KitMenu() {
    }

    public static void addButtonInOrder(ItemStack item, onClick click) {
        menu.addButton(actualSlot, item, click);
        actualSlot++;
    }

    public static ChestAPI getMenu() {
        return menu;
    }

    @EventHandler
    public void clickChest(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action action = e.getAction();
        ItemStack item = p.getItemInHand();

        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (item.getType().equals(Material.CHEST)) {
                e.setCancelled(true);
                menu.open(p);
            }
        }
    }
}
