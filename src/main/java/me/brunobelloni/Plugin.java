package me.brunobelloni;

import java.io.File;
import java.lang.reflect.Field;
import static me.brunobelloni.chests.CommonChestMenu.setCommonChestMenu;
import me.brunobelloni.events.DeathEvent;
import me.brunobelloni.events.JoinEvent;
import me.brunobelloni.kits.Pvp;
import me.brunobelloni.kits.Thor;
import me.brunobelloni.structure.CommandListTree;
import me.brunobelloni.structure.DataStructureHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import static org.bukkit.configuration.file.YamlConfiguration.loadConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    private PluginManager pluginManager = this.getServer().getPluginManager();

    private File playerFile = new File(this.getDataFolder(), "playerData.yml");
    private FileConfiguration playerConfig = loadConfiguration(playerFile);

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
        if (!playerFile.exists()) {
            saveResource(playerFile.getName(), false);
            System.out.println("Criado!");
        }

        try {
            setCommonChestMenu();
            bindEvents();
            bindCommands();
            bindKits();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindEvents() {
        this.pluginManager.registerEvents(new DataStructureHandler(this), this);

        this.pluginManager.registerEvents(new DeathEvent(this), this);
        this.pluginManager.registerEvents(new JoinEvent(this), this);
    }

    private void bindCommands() throws Exception {
        Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        bukkitCommandMap.setAccessible(true);
        CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

        commandMap.register("arvore", new CommandListTree("arvore"));

    }

    private void bindKits() throws Exception {
        Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        bukkitCommandMap.setAccessible(true);
        CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

        commandMap.register("pvp", new Pvp("pvp"));
        commandMap.register("thor", new Pvp("thor"));
        this.pluginManager.registerEvents(new Thor(this, "thor"), this);
    }
}
