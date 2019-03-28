package me.brunobelloni.types;

import java.util.List;
import java.util.UUID;
import me.brunobelloni.enums.Abilitys;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Gamer implements Comparable<Gamer> {

    private final Player player;
    private Double money;
    private List<String> availableKits;
    Abilitys ability;

    public Gamer(Player player) {
        this.player = player;
    }

    public Gamer setAbility(Abilitys ability) {
        this.ability = ability;
        return this;
    }

    public Abilitys getAbility() {
        return ability;
    }

    public Gamer removeAbility() {
        this.ability = null;
        return this;
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

    public Double getMoney() {
        return money;
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
