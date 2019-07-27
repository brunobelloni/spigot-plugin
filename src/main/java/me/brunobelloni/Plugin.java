package me.brunobelloni;

import me.brunobelloni.api.event.EventAPI;
import me.brunobelloni.api.kits.Pvp;
import me.brunobelloni.api.kits.Switcher;
import me.brunobelloni.api.kits.Thor;
import me.brunobelloni.controllers.AbilityController;
import me.brunobelloni.controllers.CooldownController;
import me.brunobelloni.controllers.MenuController;
import me.brunobelloni.listeners.ChatFormat;
import me.brunobelloni.listeners.CmdPreprocess;
import me.brunobelloni.listeners.ItemDrop;
import me.brunobelloni.listeners.UtilListeners;
import me.brunobelloni.listeners.player.*;
import me.brunobelloni.mysql.Database;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class Plugin extends JavaPlugin {

    public static Database database;

    private Field bukkitCommandMap;
    private CommandMap commandMap;
    private PluginManager pluginManager = this.getServer().getPluginManager();
    public FileConfiguration config = getConfig();

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
        try {
            loadConfiguration();
            setCommandRegister();
            database = new Database(this);
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage("[ERROR] " + e);
        } finally {
            new CooldownController();
            new AbilityController();
            new MenuController(this);
            bindCommands();
            bindEvents();
            bindKits();
        }
    }

    public void loadConfiguration() {
        config.options().copyDefaults(true);
        saveConfig();
    }

    private void bindEvents() {
        this.pluginManager.registerEvents(new EventAPI(), this);
        this.pluginManager.registerEvents(new ItemDrop(), this);
        this.pluginManager.registerEvents(new ChatFormat(), this);
        this.pluginManager.registerEvents(new JoinServer(), this);
        this.pluginManager.registerEvents(new QuitServer(), this);
        this.pluginManager.registerEvents(new AntiGriefing(), this);
        this.pluginManager.registerEvents(new PvpListeners(), this);
        this.pluginManager.registerEvents(new DeathRespawn(), this);
        this.pluginManager.registerEvents(new UtilListeners(), this);
        this.pluginManager.registerEvents(new CmdPreprocess(), this);
    }

    private void setCommandRegister() throws Exception {
        this.bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        this.bukkitCommandMap.setAccessible(true);
        this.commandMap = (CommandMap) this.bukkitCommandMap.get(Bukkit.getServer());
    }

    private void bindCommands() {
    }

    private void bindKits() {
        this.commandMap.register("pvp", new Pvp("pvp"));
        this.commandMap.register("thor", new Thor("thor"));
        this.commandMap.register("switcher", new Switcher("switcher"));
    }
}
