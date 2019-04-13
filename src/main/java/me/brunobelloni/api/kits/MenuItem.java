package me.brunobelloni.api.kits;

import me.brunobelloni.api.chest.ChestAPI.onClick;

public class MenuItem {

    public KitAPI kit;
    public String name;
    public String permission;
    public onClick click;

    public MenuItem(KitAPI kit, String name, String permission, onClick click) {
        this.kit = kit;
        this.name = name;
        this.permission = permission;
        this.click = click;
    }
}
