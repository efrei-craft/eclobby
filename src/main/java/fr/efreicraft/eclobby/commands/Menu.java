package fr.efreicraft.eclobby.commands;

import fr.efreicraft.animus.endpoints.PlayerService;
import fr.efreicraft.animus.invoker.ApiException;
import fr.efreicraft.ecatup.players.ECPlayer;
import fr.efreicraft.ecatup.utils.MessageUtils;
import fr.efreicraft.eclobby.interfaces.Boussole;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Menu implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            //TODO mettre en cache le player pour éviter les calls de DB...
            try {
                fr.efreicraft.animus.models.Player animusPlayer = PlayerService.getPlayer(player.getUniqueId().toString());
                Boussole.openBoussole(new ECPlayer(player, animusPlayer));
                return true;
            } catch (ApiException e) {
                MessageUtils.sendMessage(sender, "&cNous n'avons pas pu récupérer vos données pour ouvrir le menu.");
            }
        } else {
            MessageUtils.sendMessage(sender, "&cVous devez être un joueur pour exécuter cette commande !");
        }
        return false;
    }
}
