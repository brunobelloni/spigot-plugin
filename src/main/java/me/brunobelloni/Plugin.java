package me.brunobelloni;

import java.lang.reflect.Field;
import static me.brunobelloni.chestapi.CommonChestMenu.setCommonChestMenu;
import me.brunobelloni.dao.Database;
import me.brunobelloni.controllers.PlayerController;
import me.brunobelloni.kits.Pvp;
import me.brunobelloni.kits.Thor;
import me.brunobelloni.listeners.ChatFormat;
import me.brunobelloni.listeners.CmdPreprocess;
import me.brunobelloni.listeners.ItemDrop;
import me.brunobelloni.listeners.MobSpawn;
import me.brunobelloni.listeners.WeatherEvent;
import me.brunobelloni.listeners.player.BuildEvent;
import me.brunobelloni.listeners.player.DeathEvent;
import me.brunobelloni.listeners.player.FoodEvent;
import me.brunobelloni.listeners.player.JoinEvent;
import me.brunobelloni.listeners.player.QuitEvent;
import me.brunobelloni.listeners.pvp.Soup;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    private PluginManager pluginManager = this.getServer().getPluginManager();
    public static Database database;

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        try {
            database = new Database(this);

            setCommonChestMenu();
            bindEvents();
            bindCommands();
            bindKits();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindEvents() {
        this.pluginManager.registerEvents(new PlayerController(this), this);

        this.pluginManager.registerEvents(new DeathEvent(), this);
        this.pluginManager.registerEvents(new JoinEvent(), this);
        this.pluginManager.registerEvents(new QuitEvent(), this);
        this.pluginManager.registerEvents(new WeatherEvent(), this);
        this.pluginManager.registerEvents(new FoodEvent(), this);
        this.pluginManager.registerEvents(new ChatFormat(), this);
        this.pluginManager.registerEvents(new MobSpawn(), this);
        this.pluginManager.registerEvents(new BuildEvent(), this);
        this.pluginManager.registerEvents(new Soup(), this);
        this.pluginManager.registerEvents(new CmdPreprocess(), this);
        this.pluginManager.registerEvents(new ItemDrop(), this);
    }

    private void bindCommands() throws Exception {
        Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        bukkitCommandMap.setAccessible(true);
        CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
    }

    private void bindKits() throws Exception {
        Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        bukkitCommandMap.setAccessible(true);
        CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

        commandMap.register("pvp", new Pvp("pvp"));
        commandMap.register("thor", new Thor("thor"));
    }
}
