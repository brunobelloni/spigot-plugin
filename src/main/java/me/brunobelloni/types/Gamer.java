package me.brunobelloni.types;

import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Gamer {

    private Player gamer;
    private Double money;
    private List<String> availableKits;

    public Gamer(Player game) {
        this.gamer = gamer;
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
        ItemStack[] inventory = gamer.getInventory().getContents();

        // Percorre inventario
        return this;
    }

    public Gamer clearInventory() {
        gamer.getInventory().clear();
        return this;
    }

}
