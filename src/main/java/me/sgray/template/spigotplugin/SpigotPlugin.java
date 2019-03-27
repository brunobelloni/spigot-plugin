package me.sgray.template.spigotplugin;

import me.brunobelloni.events.LoginEvent;
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

    
    /**
     * Commands enabled with following method must have entries in plugin.yml
     */
    private void bindCommands() {
        getCommand("example").setExecutor(new ExampleCommand(this));
    }
}
