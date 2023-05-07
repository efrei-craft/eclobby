package fr.efreicraft.eclobby.commands;

import fr.efreicraft.ecatup.utils.MessageUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Lobby implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            MessageUtils.sendMessage(player, "&aVous avez été téléporté au lobby !");
            player.teleport(new Location(player.getWorld(), 0.5, 1, 0.5, 90, 0));
            return true;
        }
        else {
            MessageUtils.sendMessage(sender, "&cVous devez être un joueur pour exécuter cette commande !");
            return false;
        }
    }
}
