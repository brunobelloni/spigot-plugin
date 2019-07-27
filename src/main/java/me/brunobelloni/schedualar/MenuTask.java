package me.brunobelloni.schedualar;

import me.brunobelloni.api.chest.ChestAPI;
import me.brunobelloni.api.kits.KitAPI;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import static me.brunobelloni.controllers.MenuController.addPlayerMenu;
import static me.brunobelloni.controllers.MenuController.getKits;
import static me.brunobelloni.enums.MenuItem.GLASS;
import static me.brunobelloni.enums.Messages.KIT_CHEST_NAME;

public class MenuTask extends BukkitRunnable {

    private final Player p;
    private final boolean openWhenFinalize;

    public MenuTask(Player p, boolean openWhenFinalize) {
        this.p = p;
        this.openWhenFinalize = openWhenFinalize;
    }

    @Override
    public void run() {
        final ChestAPI chest = new ChestAPI(KIT_CHEST_NAME, 6);
        ArrayList<KitAPI> dontHaveKits = new ArrayList<>();
        Integer counter = 0;
        ArrayList<KitAPI> kits = getKits();

        for (KitAPI kit : kits) {
            if (p.hasPermission(kit.getPermission())) {
                chest.addButton(counter, kit.getItemMenu(), kit.getClick());
                counter++;
            } else {
                dontHaveKits.add(kit);
            }
        }

        for (KitAPI kit : dontHaveKits) {
            chest.addButton(counter, kit.getDontItemMenu(), kit.getClick());
            counter++;
        }

        while (counter < chest.getSize()) {
            chest.addButton(counter, GLASS);
            counter++;
        }

        addPlayerMenu(p, chest);
        if (openWhenFinalize) {
            chest.open(p);
        }
    }
}
