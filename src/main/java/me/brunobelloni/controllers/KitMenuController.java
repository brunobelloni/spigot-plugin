package me.brunobelloni.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import me.brunobelloni.Plugin;
import me.brunobelloni.api.chest.ChestAPI;
import me.brunobelloni.api.kits.KitAPI;
import static me.brunobelloni.enums.Messages.KIT_CHEST_NAME;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class KitMenuController {

    private static Plugin instance;
    private static HashMap<UUID, ChestAPI> playersMenu;
    private static ArrayList<KitAPI> kitList;

    public KitMenuController(Plugin instance) {
        this.instance = instance;
        playersMenu = new HashMap<>();
        kitList = new ArrayList<>();
    }

    public static void addKitList(KitAPI kit) {
        kitList.add(kit);
    }

    public static void addMenuOnPlayerLogin(final Player p, final boolean open) {
        final ChestAPI chest = new ChestAPI(KIT_CHEST_NAME, 6);

        new BukkitRunnable() {
            @Override
            public void run() {
                for (final KitAPI kit : kitList) {

                    System.out.println(kit.getPermission());

                    if (p.hasPermission(kit.getPermission())) {

                    } else {

                    }

//                    chest.addButton(
//                            kitList.indexOf(itemMenu),
//                            itemMenu.item,
//                            itemMenu.click
//                    );
                }
                playersMenu.put(p.getUniqueId(), chest);
                if (open) {
                    chest.open(p);
                }
            }
        }.runTaskAsynchronously(KitMenuController.instance);
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
}
