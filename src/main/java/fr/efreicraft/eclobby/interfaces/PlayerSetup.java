package fr.efreicraft.eclobby.interfaces;

import fr.efreicraft.animus.endpoints.QueueService;
import fr.efreicraft.animus.invoker.ApiException;
import fr.efreicraft.ecatup.players.ECPlayer;
import fr.efreicraft.ecatup.players.menus.ItemStackMenuItem;
import fr.efreicraft.ecatup.players.menus.PlayerInventoryMenu;
import fr.efreicraft.ecatup.players.menus.interfaces.MenuItem;
import fr.efreicraft.ecatup.players.scoreboards.ScoreboardField;
import fr.efreicraft.ecatup.utils.MessageUtils;
import fr.efreicraft.eclobby.fields.PlayerCount;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PlayerSetup {

    public static Set<String> inQueue = new HashSet<>();

    public static void setupScoreboard(ECPlayer player) {
        player.getBoard().clearFields();

        player.getBoard().setVisibility(true);

        player.getBoard().setTitle("&e&lEFREI CRAFT");

        player.getBoard().setField(
                1,
                new ScoreboardField(
                        "&7Rang:",
                        true,
                        player1 -> {
                            return player1.getAnimusPlayer().getPermGroups().get(0).getColor() + player1.getAnimusPlayer().getPermGroups().get(0).getName();
                        }
                )
        );

        player.getBoard().setField(
                2,
                new ScoreboardField(
                        "&7Joueurs en ligne:",
                        true,
                        player1 -> {
                            return "&a" + PlayerCount.playerCount;
                        }
                )
        );
    }

    public static void setupMenus(ECPlayer player) {
        List<MenuItem> items = new ArrayList<>();

        items.add(
                new ItemStackMenuItem(
                        0,
                        () -> {
                            if(inQueue.contains(player.getAnimusPlayer().getUuid())) {
                                return new ItemStackMenuItem(
                                        new ItemStack(Material.BARRIER),
                                        "&cQuitter la file&r &7• Clic droit",
                                        "&7Clic droit pour quitter la file."
                                );
                            }
                            return new ItemStackMenuItem(
                                    new ItemStack(Material.COMPASS),
                                    "&3Boussole&r &7• Clic droit",
                                    "&7Clic droit pour ouvrir le menu."
                            );
                        },
                        event -> {
                            if(inQueue.contains(player.getAnimusPlayer().getUuid())) {
                                try {
                                    QueueService.removePlayerFromQueue(player.entity().getUniqueId());
                                    MessageUtils.sendMessage(player.entity(), MessageUtils.ChatPrefix.QUEUE, "&7Vous avez &cquitté&7 la file d'attente.");
                                } catch (ApiException e) {
                                    e.printStackTrace();
                                    MessageUtils.sendMessage(player.entity(), MessageUtils.ChatPrefix.QUEUE, "&cUne erreur est survenue lors de ta suppression de la file d'attente.");
                                } finally {
                                    inQueue.remove(player.getAnimusPlayer().getUuid());
                                    PlayerSetup.setupMenus(player);
                                }
                            } else {
                                Boussole.openBoussole(player);
                            }
                        }
                )
        );

        player.getPlayerMenus().setMenu(
                "LOBBY_ITEMS",
                new PlayerInventoryMenu(
                        player,
                        items
                )
        ).show();
    }

}
