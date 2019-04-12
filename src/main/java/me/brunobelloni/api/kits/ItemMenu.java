package me.brunobelloni.api.kits;

import org.bukkit.inventory.ItemStack;

public class ItemMenu {

    public ItemStack item;
    public String name;
    public String permission;

    public ItemMenu(ItemStack item, String name, String permission) {
        this.item = item;
        this.name = name;
        this.permission = permission;
    }
}
