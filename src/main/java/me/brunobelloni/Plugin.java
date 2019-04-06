package me.brunobelloni;

import java.lang.reflect.Field;
import static me.brunobelloni.chestgui.CommonChestMenu.setCommonChestMenu;
import me.brunobelloni.events.DeathEvent;
import me.brunobelloni.events.JoinEvent;
import me.brunobelloni.kits.Pvp;
import me.brunobelloni.kits.Thor;
import me.brunobelloni.sqlite.SQLite;
import me.brunobelloni.types.HashCmdHandler;
import me.brunobelloni.types.HashHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    private PluginManager pluginManager = this.getServer().getPluginManager();
    public static SQLite database;

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {

        try {
            database = new SQLite(this);
        } catch (Exception e) {
            e.printStackTrace();
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
        this.pluginManager.registerEvents(new HashHandler(this), this);

        this.pluginManager.registerEvents(new DeathEvent(this), this);
        this.pluginManager.registerEvents(new JoinEvent(this), this);
    }

    private void bindCommands() throws Exception {
        Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        bukkitCommandMap.setAccessible(true);
        CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

        commandMap.register("arvore", new HashCmdHandler("arvore"));

    }

    private void bindKits() throws Exception {
        Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        bukkitCommandMap.setAccessible(true);
        CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

        commandMap.register("pvp", new Pvp("pvp"));
        commandMap.register("thor", new Thor("thor"));
    }
}
