package me.brunobelloni.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.Plugin;
import me.brunobelloni.api.chest.ChestAPI;
import me.brunobelloni.api.kits.ItemMenu;
import static me.brunobelloni.enums.Messages.KIT_CHEST_NAME;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

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

        BukkitScheduler scheduler = plugin.getServer().getScheduler();

        new BukkitRunnable() {

            @Override
            public void run() {
                for (final ItemMenu itemMenu : kitList) {
                    chest.addButton(
                            kitList.indexOf(itemMenu),
                            itemMenu.item,
                            itemMenu.click
                    );
                    System.out.println(itemMenu.name + " inserido para " + p.getName());
                }
                playersMenu.put(p.getUniqueId(), chest);
                if (open) {
                    chest.open(p);
                }
            }

        }.runTaskAsynchronously(KitMenuController.plugin);
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
