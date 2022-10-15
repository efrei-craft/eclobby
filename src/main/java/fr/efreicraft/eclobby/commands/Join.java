package fr.efreicraft.eclobby.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
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
                player.sendMessage("§7Téléportation sur le serveur " + args[0] + "...");
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF(args[0]);
                player.sendPluginMessage(Main.getPlugin(Main.class), "BungeeCord", out.toByteArray());
                return true;
            }
            else {
                sender.sendMessage("§cUtilisation: /join <serveur>");
                return false;
            }
        }
        else {
            sender.sendMessage("§cVous devez être un joueur pour exécuter cette commande !");
            return false;
        }
    }
}
