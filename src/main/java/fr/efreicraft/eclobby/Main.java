package fr.efreicraft.eclobby;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.efreicraft.eclobby.commands.Join;
import fr.efreicraft.eclobby.commands.Lobby;
import fr.efreicraft.eclobby.commands.Menu;
import fr.efreicraft.eclobby.listeners.*;
import fr.efreicraft.eclobby.utils.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.Objects;

public final class Main extends JavaPlugin {

    public static JavaPlugin INSTANCE;

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;

        getServer().getMessenger().registerOutgoingPluginChannel(INSTANCE, "BungeeCord");
        getServer().getMessenger().registerOutgoingPluginChannel(INSTANCE, "servo:queue");

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
        if (!player.hasPermission("server." + server.toLowerCase())) {
            Component nope = Component.text("Vous ne pouvez pas aller sur ce serveur !").color(NamedTextColor.RED);
            player.sendMessage(nope);
            INSTANCE.getLogger().info(player.getName() + " tried to join " + server + " but doesn't have permission to do so.");
            return;
        }

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(INSTANCE, "BungeeCord", out.toByteArray());
    }

    public static void queuePlayer(Player player, String minigame) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(minigame);
        player.sendPluginMessage(INSTANCE, "servo:queue", out.toByteArray());
    }

    void setupBoussole() {
        var menuBoussole = new ItemStack(Material.COMPASS);

        ItemMeta meta = menuBoussole.getItemMeta();

        meta.displayName(Component.text("Menu", NamedTextColor.GREEN, TextDecoration.BOLD));
        meta.lore(Collections.singletonList(Component.text(Utils.colorize("&a&lClic droit pour ouvrir le menu !"))));
        menuBoussole.setItemMeta(meta);

        Login.menuBoussole = menuBoussole;
    }

}
