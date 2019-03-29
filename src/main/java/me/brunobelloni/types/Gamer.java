package me.brunobelloni.types;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static me.brunobelloni.chests.CommonChestMenu.getMenu;
import me.brunobelloni.chests.IconMenu;
import me.brunobelloni.enums.Abilitys;
import me.brunobelloni.enums.CustomItem;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Gamer implements Comparable<Gamer> {

    private final Player player;
    private Double money;
    private List<String> availableKits;
    boolean isOnCooldown;
    Abilitys ability;
    IconMenu menu;

    public Gamer() {
        this.player = null;
    }

    public Gamer(Player player) {
        this.player = player;
        this.money = 0.0;
        this.availableKits = new ArrayList<>();
        this.ability = Abilitys.NONE;
        this.menu = getMenu();
        this.isOnCooldown = false;
    }

    public Gamer giveItem(ItemStack item) {
        player.getInventory().addItem(item);
        return this;
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

        ItemStack soup = CustomItem.SOUP.getItem();

        for (ItemStack item : inventory) {
            if (item == null) {
                player.getInventory().addItem(soup);
            }
        }

        return this;
    }

    public Gamer clearInventory() {
        player.getInventory().clear();
        return this;
    }

    public Gamer playSound(Sound sound) {
        player.playSound(player.getLocation(), sound, 1, 1);
        return this;
    }

    public Gamer sendMessage(String message) {
        player.sendMessage(message);
        return this;
    }

    public Gamer giveMenuItens() {
        this.player.sendMessage("Implementar os itens iniciais!");
        return this;
    }

    public Gamer openInventoryMenu() {
        menu.open(this.player);
        return this;
    }

    public boolean isOnCooldown() {
        return this.isOnCooldown;
    }

    public Gamer putCooldown() {
        this.isOnCooldown = true;
        return this;
    }

    public Gamer removeCooldown() {
        this.isOnCooldown = false;
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
