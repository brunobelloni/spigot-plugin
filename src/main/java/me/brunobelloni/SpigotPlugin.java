package me.brunobelloni;

import java.lang.reflect.Field;
import me.brunobelloni.commands.ExampleCommand;
import me.brunobelloni.commands.Sample;
import me.brunobelloni.events.LoginEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotPlugin extends JavaPlugin {

    PluginManager pluginManager = this.getServer().getPluginManager();

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
        bindEvents();
        bindCommands();
    }

    private void bindEvents() {
        this.pluginManager.registerEvents(new LoginEvent(this), this);
    }

    private void bindCommands() {
        this.getCommand("example").setExecutor(new ExampleCommand(this));

        try {
            Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            this.bindSingleCommand(commandMap, "seen");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindSingleCommand(CommandMap commandMap, String cmd) {
        commandMap.register(cmd, new Sample(cmd));
    }
}
