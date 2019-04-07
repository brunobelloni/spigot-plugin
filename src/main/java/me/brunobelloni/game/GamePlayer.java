package me.brunobelloni.game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static me.brunobelloni.api.chest.KitMenu.getMenu;
import me.brunobelloni.api.chest.ChestAPI;
import me.brunobelloni.enums.Abilitys;
import me.brunobelloni.enums.CustomItem;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GamePlayer implements Comparable<GamePlayer> {

    private final Player player;
    private Double money;
    private Integer kills;
    private Integer deaths;

    private List<String> availableKits;
    private long cooldown;
    private Abilitys ability;
    private ChestAPI menu;

    public GamePlayer() {
        this.player = null;
    }

    public GamePlayer(Player player) {
        this.player = player;
        this.money = 0.0;
        this.availableKits = new ArrayList<>();
        this.ability = Abilitys.NONE;
        this.menu = getMenu();
        this.cooldown = 0L;
    }

    public GamePlayer giveItem(ItemStack item) {
        player.getInventory().addItem(item);
        return this;
    }

    public GamePlayer setAbility(Abilitys ability) {
        this.ability = ability;
        return this;
    }

    public Abilitys getAbility() {
        return ability;
    }

    public GamePlayer removeAbility() {
        this.ability = Abilitys.NONE;
        return this;
    }

    public Player getPlayer() {
        return this.player;
    }

    public UUID getUUID() {
        return player.getUniqueId();
    }

    public GamePlayer setMoney(Double money) {
        this.money = money;
        return this;
    }

    public Double getMoney() {
        return money;
    }

    public GamePlayer addMoney(Double money) {
        this.money += money;
        return this;
    }

    public GamePlayer removeMoney(Double money) {
        this.money -= money;
        return this;
    }

    public GamePlayer fillInventoryWithSoup() {
        ItemStack[] inventory = player.getInventory().getContents();

        ItemStack soup = CustomItem.SOUP.getItem();

        for (ItemStack item : inventory) {
            if (item == null) {
                player.getInventory().addItem(soup);
            }
        }

        return this;
    }

    public GamePlayer clearInventory() {
        player.getInventory().clear();
        return this;
    }

    public GamePlayer playSound(Sound sound) {
        player.playSound(player.getLocation(), sound, 1, 1);
        return this;
    }

    public GamePlayer sendMessage(String message) {
        player.sendMessage(message);
        return this;
    }

    public GamePlayer giveMenuItens() {
        this.player.sendMessage("Implementar os itens iniciais!");
        return this;
    }

    public GamePlayer openInventoryMenu() {
        menu.open(this.player);
        return this;
    }

    public boolean isOnCooldown() {
        return this.cooldown != 0L;
    }

    public GamePlayer putCooldown(long cooldown) {
        this.cooldown = cooldown;
        return this;
    }

    public GamePlayer removeCooldown() {
        this.cooldown = 0L;
        return this;
    }

    public long getCooldown() {
        return this.cooldown;
    }

    @Override
    public String toString() {
        return player.getName();
    }

    @Override
    public int compareTo(GamePlayer gamer) {
        return this.player.getUniqueId().compareTo(gamer.getUUID());
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }
}
