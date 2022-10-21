package fr.efreicraft.eclobby.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class AdminPortal implements Listener {
    @EventHandler
    public void onGoToPortal(PlayerMoveEvent event) {
        Location loc = event.getPlayer().getLocation();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        if (x == 41.5 && y >= 52.0 && y <= 57.0 && z >= -0.7 && z <= 1.7) {
            event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), -12.5, 50, 0.5, -90, 0));
        }
    }
}
