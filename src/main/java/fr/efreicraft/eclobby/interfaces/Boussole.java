package fr.efreicraft.eclobby.interfaces;

import fr.efreicraft.animus.endpoints.GameService;
import fr.efreicraft.animus.endpoints.PlayerService;
import fr.efreicraft.animus.endpoints.QueueService;
import fr.efreicraft.animus.invoker.ApiException;
import fr.efreicraft.animus.models.Game;
import fr.efreicraft.animus.models.GameServer;
import fr.efreicraft.ecatup.players.ECPlayer;
import fr.efreicraft.ecatup.players.menus.ChestMenu;
import fr.efreicraft.ecatup.players.menus.ItemStackMenuItem;
import fr.efreicraft.ecatup.players.menus.interfaces.MenuItem;
import fr.efreicraft.ecatup.utils.MessageUtils;
import fr.efreicraft.eclobby.fields.AvailableGames;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Boussole {

    public static void openBoussole(ECPlayer player) {
        List<MenuItem> items = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            items.add(
                    new ItemStackMenuItem(
                            new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                            i * 9 + 1,
                            "",
                            "",
                            event -> {}
                    )
            );
        }

        int currentSlot = 12;
        for (Game game : AvailableGames.games) {
            items.add(
                    new ItemStackMenuItem(
                            currentSlot,
                            () -> {
                                String description = game.getMenuDescription();
                                description = description.replaceAll("\\\\n", System.lineSeparator());

                                return new ItemStackMenuItem(
                                        new ItemStack(Material.getMaterial(game.getMenuMaterial())),
                                        game.getColor() + "&l" + game.getDisplayName(),
                                        "&7 \n&7" + description + "\n&7 \n&7Joueurs en ligne: &a" + AvailableGames.playerCountPerGame.get(game.getName()) + "\n&7 \n&8» &6Clic droit pour rejoindre"
                                );
                            },
                            event -> {
                                try {
                                    QueueService.addPlayerToQueue(player.entity().getUniqueId(), game.getName());
                                    player.getPlayerMenus().getMenu("BOUSSOLE").close();
                                    PlayerSetup.inQueue.add(player.getAnimusPlayer().getUuid());
                                    PlayerSetup.setupMenus(player);
                                } catch (ApiException e) {
                                    MessageUtils.sendMessage(player.entity(), MessageUtils.ChatPrefix.QUEUE, "&cUne erreur est survenue lors de l'ajout à la file d'attente.");
                                }
                            }
                    )
            );
            if(currentSlot % 9 == 8) currentSlot += 5;
            else currentSlot++;
        }

        player.getPlayerMenus().setMenu(
                "BOUSSOLE",
                new ChestMenu(
                        player,
                        "&8» &3Boussole",
                        9 * 5,
                        items
                )
        ).show();
    }

}
