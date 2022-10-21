package fr.efreicraft.eclobby;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import fr.efreicraft.eclobby.commands.*;
import fr.efreicraft.eclobby.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;

import java.util.Collections;
import java.util.Objects;

public final class Main extends JavaPlugin {

    public static JavaPlugin INSTANCE;

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;

        getServer().getMessenger().registerOutgoingPluginChannel(INSTANCE, "BungeeCord");

        setupBoussole();

        Bukkit.getPluginManager().registerEvents(new Login(), INSTANCE);
        Bukkit.getPluginManager().registerEvents(new GamemodeChange(), INSTANCE);
        Bukkit.getPluginManager().registerEvents(new Click(), INSTANCE);
        Bukkit.getPluginManager().registerEvents(new Damage(), INSTANCE);
        Bukkit.getPluginManager().registerEvents(new TechZone(), INSTANCE);
        Bukkit.getPluginManager().registerEvents(new TapisRoulant(), INSTANCE);
        Bukkit.getPluginManager().registerEvents(new AdminPortal(), INSTANCE);

        registerCommand("lobby", new Lobby());
        registerCommand("join", new Join());
        registerCommand("menu", new Menu());
        for (Player player : Bukkit.getOnlinePlayers()) {
            fr.efreicraft.eclobby.utils.HUDManager.setScoreboard(player);
            Location loc = player.getLocation();
            if (loc.getY() < -30) {
                player.teleport(new Location(player.getWorld(), 0.5, 1, 0.5, 90, 0));
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getMessenger().unregisterOutgoingPluginChannel(INSTANCE);
    }

    void registerCommand(String command, CommandExecutor executor) {
        Objects.requireNonNull(Bukkit.getPluginCommand(command)).setExecutor(executor);
    }

    public static void sendPlayerToServer(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(INSTANCE, "BungeeCord", out.toByteArray());
    }

    void setupBoussole() {
        var menuBoussole = new ItemStack(Material.COMPASS);

        ItemMeta meta = menuBoussole.getItemMeta();

        meta.displayName(Component.text("Menu", NamedTextColor.GREEN, TextDecoration.BOLD));
        meta.lore(Collections.singletonList(Component.text(colorize("&a&lClic droit pour ouvrir le menu !"))));
        menuBoussole.setItemMeta(meta);

        Login.menuBoussole = menuBoussole;
    }

    public static String colorize(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
