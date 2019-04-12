package me.brunobelloni.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.Plugin;
import me.brunobelloni.api.chest.ChestAPI;
import me.brunobelloni.api.chest.ChestAPI.onClick;
import me.brunobelloni.api.kits.ItemMenu;
import static me.brunobelloni.enums.Messages.KIT_CHEST_NAME;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class KitMenuController {

    private static Plugin plugin;
    private static HashMap<UUID, ChestAPI> playersMenu;
    private static ArrayList<ItemMenu> kitList;

    public KitMenuController(Plugin plugin) {
        this.plugin = plugin;
        playersMenu = new HashMap<>();
        kitList = new ArrayList<>();
    }

    public static void addKitList(ItemMenu item) {
        kitList.add(item);
    }

    public static void addMenuOnPlayerLogin(final Player p, final boolean open) {
        final ChestAPI chest = new ChestAPI(KIT_CHEST_NAME, 6);

        Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                int i = 0;
                for (final ItemMenu itemMenu : kitList) {
                    chest.addButton(i, itemMenu.item, new onClick() {
                        @Override
                        public boolean click(Player clicker) {
                            clicker.performCommand(itemMenu.name);
                            return true;
                        }
                    });
                    System.out.println(itemMenu.name + " inserido para " + p.getName());
                    i++;
                }
                playersMenu.put(p.getUniqueId(), chest);
                if (open) {
                    chest.open(p);
                }
            }
        });
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
        System.out.println("removido");
    }
}
