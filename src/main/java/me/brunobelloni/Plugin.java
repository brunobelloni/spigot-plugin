package me.brunobelloni;

import java.io.File;
import java.lang.reflect.Field;
import me.brunobelloni.kits.Pvp;
import me.brunobelloni.structure.CommandListTree;
import me.brunobelloni.structure.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    private PluginManager pluginManager = this.getServer().getPluginManager();

    private File playerFile = new File(this.getDataFolder(), "playerData.yml");
    private FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);

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
            bindEvents();
            bindCommands();
            bindKits();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindEvents() {
        this.pluginManager.registerEvents(new PlayerHandler(this), this);
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
    }
}
