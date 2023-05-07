package fr.efreicraft.eclobby.listeners;

import fr.efreicraft.ecatup.players.ECPlayer;
import fr.efreicraft.ecatup.players.events.ECPlayerJoined;
import fr.efreicraft.ecatup.utils.MessageUtils;
import fr.efreicraft.eclobby.ECLobby;
import fr.efreicraft.eclobby.interfaces.PlayerSetup;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Login implements Listener {

    public static ItemStack menuBoussole;

    private static final int DEATH_TP_BARRIER = -30;

    @EventHandler
    public void onPlayerJoin(ECPlayerJoined event) {
        ECPlayer player = event.getPlayer();
        org.bukkit.entity.Player bukkitPlayer = player.entity();

        player.resetPlayer();

        Bukkit.getScheduler().runTaskLater(ECLobby.INSTANCE, () -> {
            bukkitPlayer.setGameMode(GameMode.ADVENTURE);
            if (bukkitPlayer.hasPermission("eclobby.fly")) {
                bukkitPlayer.setAllowFlight(true);
            }
            bukkitPlayer.teleport(new Location(bukkitPlayer.getWorld(), 0.5, 1, 0.5, 90, 0));
        }, 1L);

        PlayerSetup.setupScoreboard(player);
        PlayerSetup.inQueue.remove(player.getAnimusPlayer().getUuid());
        PlayerSetup.setupMenus(player);

        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                Location loc = bukkitPlayer.getLocation();
                if (loc.getY() < DEATH_TP_BARRIER) {
                    bukkitPlayer.teleport(new Location(bukkitPlayer.getWorld(), 0.5, 1, 0.5, 90, 0));
                }
            }
        };
        runnable.run();

        runnable.runTaskTimer(ECLobby.INSTANCE,0,20L);

        if(player.getAnimusPlayer().getPermGroups().get(0).getPriority().intValue() > 0) {
            MessageUtils.broadcastMessage("&7" + player.getChatName() + " &7a rejoint le serveur !");
        }

        MessageUtils.sendMessage(bukkitPlayer, "\n&7Bienvenue sur &e&lEFREI CRAFT&7, " + player.getChatName() + "&7 !\nOuvre ta boussole pour commencer à jouer !\n");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent quit) {
        quit.quitMessage(null);
    }
}
