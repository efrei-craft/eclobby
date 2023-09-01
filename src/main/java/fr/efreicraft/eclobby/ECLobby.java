package fr.efreicraft.eclobby;

import fr.efreicraft.eclobby.commands.Lobby;
import fr.efreicraft.eclobby.commands.Menu;
import fr.efreicraft.eclobby.fields.AvailableGames;
import fr.efreicraft.eclobby.fields.PlayerCount;
import fr.efreicraft.eclobby.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ECLobby extends JavaPlugin {

    public static JavaPlugin INSTANCE;

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;

        Bukkit.getPluginManager().registerEvents(new Login(), INSTANCE);
        Bukkit.getPluginManager().registerEvents(new GamemodeChange(), INSTANCE);
        Bukkit.getPluginManager().registerEvents(new Click(), INSTANCE);
        Bukkit.getPluginManager().registerEvents(new Damage(), INSTANCE);
        Bukkit.getPluginManager().registerEvents(new TechZone(), INSTANCE);
        Bukkit.getPluginManager().registerEvents(new TapisRoulant(), INSTANCE);
        Bukkit.getPluginManager().registerEvents(new AdminPortal(), INSTANCE);

        registerCommand("lobby", new Lobby());
        registerCommand("menu", new Menu());

        AvailableGames.startRefreshing();
        PlayerCount.startRefreshing();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getMessenger().unregisterOutgoingPluginChannel(INSTANCE);
    }

    void registerCommand(String command, CommandExecutor executor) {
        Objects.requireNonNull(Bukkit.getPluginCommand(command)).setExecutor(executor);
    }

    public static JavaPlugin getInstance() {
        return INSTANCE;
    }

}
