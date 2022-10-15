package fr.efreicraft.eclobby;

import org.bukkit.plugin.java.JavaPlugin;
import fr.efreicraft.eclobby.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;

import java.util.Objects;

public final class Main extends JavaPlugin {

    public static JavaPlugin INSTANCE;

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;
        getServer().getMessenger().registerOutgoingPluginChannel(INSTANCE, "BungeeCord");

        registerCommand("lobby", new Lobby());
        registerCommand("join", new Join());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getMessenger().unregisterOutgoingPluginChannel(INSTANCE);
    }

    void registerCommand(String command, CommandExecutor executor) {
        Objects.requireNonNull(Bukkit.getPluginCommand(command)).setExecutor(executor);
    }
}
