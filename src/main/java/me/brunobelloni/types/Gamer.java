package me.brunobelloni.types;

import java.util.List;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Gamer implements Comparable<Gamer> {

    private final Player player;
    private Double money;
    private List<String> availableKits;

    public Gamer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public UUID getUUID() {
        return player.getUniqueId();
    }

    public Gamer setMoney(Double money) {
        this.money = money;
        return this;
    }

    public Gamer addMoney(Double money) {
        this.money += money;
        return this;
    }

    public Gamer removeMoney(Double money) {
        this.money -= money;
        return this;
    }

    public Gamer fillInventoryWithSoup() {
        ItemStack[] inventory = player.getInventory().getContents();

        // Percorre inventario
        return this;
    }

    public Gamer clearInventory() {
        player.getInventory().clear();
        return this;
    }

    @Override
    public String toString() {
        return player.getName();
    }

    @Override
    public int compareTo(Gamer gamer) {
        return this.player.getUniqueId().compareTo(gamer.getUUID());
    }
}
