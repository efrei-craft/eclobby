package fr.efreicraft.eclobby.commands;

import fr.efreicraft.eclobby.listeners.MenuGUI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static fr.efreicraft.eclobby.Main.INSTANCE;

public class Menu implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            MenuGUI menu = new MenuGUI(player);
            Bukkit.getPluginManager().registerEvents(menu, INSTANCE);
            menu.openInventory(player);
            return true;
        }
        return false;
    }
}
