package me.brunobelloni.api.kits;

import me.brunobelloni.Plugin;
import me.brunobelloni.api.chest.ChestAPI;
import me.brunobelloni.api.chest.ChestAPI.onClick;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.text.MessageFormat;

import static me.brunobelloni.controllers.MenuController.addKit;
import static me.brunobelloni.enums.Messages.COMMAND_FROM_CONSOLE;
import static me.brunobelloni.enums.Messages.DONT_HAVE_PERMISSION;

public abstract class KitAPI extends BukkitCommand implements Listener {

    private String name;
    private Plugin instance;
    private ItemStack item;
    private ItemStack itemMenu;
    private ItemStack dontItemMenu;
    private Integer kitCooldown;

    private onClick click = new ChestAPI.onClick() {
        @Override
        public void click(Player clicker) {
            clicker.performCommand(name);
        }
    };

    public KitAPI(final String name) {
        super(name);
        this.name = name;
        this.instance = (Plugin) Bukkit.getPluginManager().getPlugins()[0];
        this.description = MessageFormat.format("Escolha o kit {0}", name);
        this.usageMessage = MessageFormat.format("/{0}", name);
        this.setPermission(MessageFormat.format("kit.{0}", name));
        addKit(this);
        Bukkit.getPluginManager().registerEvents(this, instance);
    }

    public Plugin getInstance() {
        return instance;
    }

    public void setInstance(Plugin instance) {
        this.instance = instance;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public ItemStack getItemMenu() {
        return itemMenu;
    }

    public void setItemMenu(ItemStack itemMenu) {
        this.itemMenu = itemMenu;
    }

    public ItemStack getDontItemMenu() {
        return dontItemMenu;
    }

    public void setDontItemMenu(ItemStack dontItemMenu) {
        this.dontItemMenu = dontItemMenu;
    }

    public Integer getKitCooldown() {
        return kitCooldown;
    }

    public void setKitCooldown(Integer kitCooldown) {
        this.kitCooldown = kitCooldown;
    }

    public onClick getClick() {
        return click;
    }

    public void setClick(onClick click) {
        this.click = click;
    }

    protected boolean commandSenderIsPlayer(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(COMMAND_FROM_CONSOLE);
            return false;
        }
        return true;
    }

    protected boolean playerHasPermission(CommandSender sender) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(DONT_HAVE_PERMISSION);
            return false;
        }
        return true;
    }
}
