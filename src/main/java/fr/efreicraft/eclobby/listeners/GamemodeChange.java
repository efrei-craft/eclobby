package fr.efreicraft.eclobby.listeners;

import fr.efreicraft.eclobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class GamemodeChange implements Listener {
    @EventHandler
    public void onGamemodeChange(PlayerGameModeChangeEvent event) {
        boolean isPlayerFlying = event.getPlayer().isFlying();
        if (event.getPlayer().hasPermission("eclobby.fly")) {
            Bukkit.getScheduler().runTaskLater(Main.INSTANCE, () -> {
                event.getPlayer().setAllowFlight(true);
                event.getPlayer().setFlying(isPlayerFlying);
            }, 1L);
        }
    }
}
