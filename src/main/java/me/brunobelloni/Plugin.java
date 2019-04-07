package me.brunobelloni;

import java.lang.reflect.Field;
import static me.brunobelloni.chestapi.CommonChestMenu.setCommonChestMenu;
import me.brunobelloni.controllers.PlayerController;
import me.brunobelloni.dao.Database;
import me.brunobelloni.kits.Pvp;
import me.brunobelloni.kits.Thor;
import me.brunobelloni.listeners.ChatFormat;
import me.brunobelloni.listeners.CmdPreprocess;
import me.brunobelloni.listeners.ItemDrop;
import me.brunobelloni.listeners.UtilListeners;
import me.brunobelloni.listeners.player.AntiGriefing;
import me.brunobelloni.listeners.player.DeathRespawn;
import me.brunobelloni.listeners.player.JoinServer;
import me.brunobelloni.listeners.player.QuitServer;
import me.brunobelloni.listeners.pvp.Soup;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    public static Database database;

    private Field bukkitCommandMap;
    private CommandMap commandMap;
    private PluginManager pluginManager = this.getServer().getPluginManager();

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        try {
            database = new Database();
            setCommandRegister();

        } catch (Exception e) {
            System.out.println("[ERROR] " + e);
        } finally {

            setCommonChestMenu();
            bindCommands();
            bindEvents();
            bindKits();
        }
    }

    private void bindEvents() {
        this.pluginManager.registerEvents(new PlayerController(this), this);

        this.pluginManager.registerEvents(new Soup(), this);
        this.pluginManager.registerEvents(new ItemDrop(), this);
        this.pluginManager.registerEvents(new ChatFormat(), this);
        this.pluginManager.registerEvents(new JoinServer(), this);
        this.pluginManager.registerEvents(new QuitServer(), this);
        this.pluginManager.registerEvents(new AntiGriefing(), this);
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
    }
}
