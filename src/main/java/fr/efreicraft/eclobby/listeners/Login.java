package fr.efreicraft.eclobby.listeners;

import fr.efreicraft.eclobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Login implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Bukkit.getScheduler().runTaskLater(Main.INSTANCE, () -> {
            player.setGameMode(GameMode.ADVENTURE);
            if (player.hasPermission("eclobby.fly")) {
                player.setAllowFlight(true);
            }
            player.teleport(new Location(player.getWorld(), 0.5, 1, 0.5, 90, 0));
        }, 1L);
    }
}
