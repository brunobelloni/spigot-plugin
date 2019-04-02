package me.brunobelloni.yml;

import java.io.File;
import me.brunobelloni.Plugin;
import org.bukkit.configuration.file.FileConfiguration;
import static org.bukkit.configuration.file.YamlConfiguration.loadConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class YmlHandler implements Listener {

    private Plugin plugin;
    private File playerFile = new File(plugin.getDataFolder(), "stats.yml");
    private FileConfiguration playerConfig = loadConfiguration(playerFile);

    public YmlHandler(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent playerLoginEvent) {

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent playerQuitEvent) {
    }

}
