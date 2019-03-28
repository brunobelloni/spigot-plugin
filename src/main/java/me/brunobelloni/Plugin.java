package me.brunobelloni;

import java.lang.reflect.Field;
import me.brunobelloni.commands.Sample;
import me.brunobelloni.avl.PlayerLoginRegistration;
import me.brunobelloni.kits.Pvp;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    PluginManager pluginManager = this.getServer().getPluginManager();

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
        try {
            bindEvents();
            bindCommands();
            bindKits();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindEvents() {
        this.pluginManager.registerEvents(new PlayerLoginRegistration(this), this);
    }

    private void bindCommands() throws Exception {
        Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        bukkitCommandMap.setAccessible(true);
        CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
        
        commandMap.register("arvore", new Sample("arvore"));

    }

    private void bindKits() throws Exception {
        Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        bukkitCommandMap.setAccessible(true);
        CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

        commandMap.register("pvp", new Pvp("pvp"));
    }
}
