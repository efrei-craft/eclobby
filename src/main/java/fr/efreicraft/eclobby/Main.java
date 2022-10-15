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

        registerCommand("lobby", new Lobby());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    void registerCommand(String command, CommandExecutor executor) {
        Objects.requireNonNull(Bukkit.getPluginCommand(command)).setExecutor(executor);
    }
}
