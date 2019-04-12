package me.brunobelloni.api.kits;

import me.brunobelloni.api.chest.ChestAPI.onClick;
import org.bukkit.inventory.ItemStack;

public class ItemMenu {

    public ItemStack item;
    public String name;
    public String permission;
    public onClick click;

    public ItemMenu(ItemStack item, String name, String permission, onClick click) {
        this.item = item;
        this.name = name;
        this.permission = permission;
        this.click = click;
    }
}
