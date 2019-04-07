package me.brunobelloni.api.chest;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ChestAPI implements Listener {

    private int size;
    private String name;
    private onClick[] click;
    private Inventory inventory;
    private List<String> viewing = new ArrayList<>();

    public ChestAPI(String name, int size) {
        this.name = name;
        this.size = size * 9;
        this.click = new onClick[this.size];
        this.inventory = Bukkit.createInventory(null, this.size, this.name);
    }

    public ChestAPI open(Player p) {
        p.openInventory(inventory);
        viewing.add(p.getName());
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

    public List<Player> getViewers() {
        List<Player> viewers = new ArrayList<>();
        for (String s : viewing) {
            viewers.add(Bukkit.getPlayer(s));
        }
        return viewers;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (viewing.contains(e.getWhoClicked().getName())) {
            Player p = (Player) e.getWhoClicked();
            ItemStack clicked = e.getCurrentItem();
            Inventory inventory = e.getInventory();

            if (clicked == null || clicked.getType().equals(Material.AIR)) {
                return;
            }

            e.setCancelled(true);
            if (!click[e.getSlot()].click(p)) {
                close(p);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (viewing.contains(event.getPlayer().getName())) {
            viewing.remove(event.getPlayer().getName());
        }
    }

    @EventHandler
    public void onPluginDisable(PluginDisableEvent event) {
        for (Player p : this.getViewers()) {
            close(p);
        }
    }

    public interface onClick {

        public abstract boolean click(Player clicker);
    }
}
