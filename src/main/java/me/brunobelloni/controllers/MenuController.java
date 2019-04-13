package me.brunobelloni.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.Plugin;
import me.brunobelloni.api.chest.ChestAPI;
import me.brunobelloni.api.kits.KitAPI;
import me.brunobelloni.schedualar.MenuTask;
import org.bukkit.entity.Player;

public class MenuController {

    private static Plugin instance;
    private static HashMap<UUID, ChestAPI> playersMenu;
    private static ArrayList<KitAPI> kits;

    public MenuController(Plugin instance) {
        this.instance = instance;
        playersMenu = new HashMap<>();
        kits = new ArrayList<>();
    }

    public static void addKit(KitAPI kit) {
        kits.add(kit);
    }

    public static ArrayList<KitAPI> getKits() {
        return kits;
    }

    public static void addMenuOnPlayerLogin(Player p, boolean open) {
        new MenuTask(p, open).runTaskAsynchronously(instance);
    }

    public static void openKitMenu(Player p) {
        ChestAPI menu = playersMenu.get(p.getUniqueId());
        if (menu == null) {
            addMenuOnPlayerLogin(p, true);
        } else {
            menu.open(p);
        }
    }

    public static void removeMenuOnPlayerQuit(Player p) {
        playersMenu.remove(p.getUniqueId());
    }

    public static void addPlayerMenu(Player p, ChestAPI chest) {
        playersMenu.put(p.getUniqueId(), chest);
    }
}
