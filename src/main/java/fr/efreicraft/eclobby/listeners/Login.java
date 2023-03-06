package fr.efreicraft.eclobby.listeners;

import fr.efreicraft.ecatup.players.Player;
import fr.efreicraft.ecatup.players.events.ECPlayerJoined;
import fr.efreicraft.ecatup.players.scoreboards.ScoreboardField;
import fr.efreicraft.ecatup.utils.MessageUtils;
import fr.efreicraft.eclobby.Main;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Login implements Listener {

    public static ItemStack menuBoussole;

    @EventHandler
    public void onPlayerJoin(ECPlayerJoined event) {
        Player player = event.getPlayer();
        org.bukkit.entity.Player bukkitPlayer = player.entity();

        player.resetPlayer();

        Bukkit.getScheduler().runTaskLater(Main.INSTANCE, () -> {
            bukkitPlayer.setGameMode(GameMode.ADVENTURE);
            if (bukkitPlayer.hasPermission("eclobby.fly")) {
                bukkitPlayer.setAllowFlight(true);
            }
            bukkitPlayer.teleport(new Location(bukkitPlayer.getWorld(), 0.5, 1, 0.5, 90, 0));
        }, 1L);

        setupScoreboard(player);

        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                Location loc = bukkitPlayer.getLocation();
                if (loc.getY() < -30) {
                    bukkitPlayer.teleport(new Location(bukkitPlayer.getWorld(), 0.5, 1, 0.5, 90, 0));
                }
            }
        };
        runnable.run();

        runnable.runTaskTimer(Main.INSTANCE,0,20L);

        if(player.getAnimusPlayer().getPermGroups().get(0).getPriority().intValue() > 0) {
            MessageUtils.broadcastMessage("&7" + player.getChatName() + " &7a rejoint le serveur !");
        }

        MessageUtils.sendMessage(bukkitPlayer, "\n&7Bienvenue sur &e&lEFREI CRAFT&7, " + player.getChatName() + "&7 !\nOuvre ta boussole pour commencer à jouer !\n");

        bukkitPlayer.getInventory().setItem(0, menuBoussole);
    }

    public void setupScoreboard(Player player) {
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
    }
}
