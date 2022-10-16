package fr.efreicraft.eclobby.commands;

import fr.efreicraft.eclobby.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Join implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                Player player = (Player) sender;
                player.sendMessage("§7Vous allez rejoindre " + args[0] + "...");
                switch (args[0]) {
                    case "vanilla" -> Main.sendPlayerToServer(player, "vanillasurvival");
                    case "modded" -> Main.sendPlayerToServer(player, "moddedsurvival");
                }
                return true;
            }
            else {
                sender.sendMessage("§cUtilisation: /join <mode de jeu>");
                return false;
            }
        }
        else {
            sender.sendMessage("§cVous devez être un joueur pour exécuter cette commande !");
            return false;
        }
    }
}
