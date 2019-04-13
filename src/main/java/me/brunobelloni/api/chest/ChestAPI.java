package me.brunobelloni.api.chest;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ChestAPI implements Listener {

    private int size;
    private String name;
    private onClick[] click;
    private Inventory inventory;

    public ChestAPI(String name, int size) {
        this.name = name;
        this.size = size * 9;
        this.click = new onClick[this.size];
        this.inventory = Bukkit.createInventory(null, this.size, this.name);
        Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().getPlugins()[0]);
    }

    public ChestAPI open(Player p) {
        p.openInventory(inventory);
        return this;
    }

    public ChestAPI close(Player p) {
        if (p.getOpenInventory().getTitle().equals(name)) {
            p.closeInventory();
        }
        return this;
    }

    public ChestAPI addButton(int position, ItemStack item, onClick click) {
        this.inventory.setItem(position, item);
        this.click[position] = click;
        return this;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (p.getOpenInventory().getTitle().equals(name)) {
            ItemStack clicked = e.getCurrentItem();
            Inventory inventory = e.getInventory();
            e.setCancelled(true);

            if (clicked == null || clicked.getType().equals(Material.AIR)) {
                return;
            }

            click[e.getSlot()].click(p);
            p.getOpenInventory().close();
        }
    }

    public interface onClick {

        public abstract void click(Player clicker);
    }
}
