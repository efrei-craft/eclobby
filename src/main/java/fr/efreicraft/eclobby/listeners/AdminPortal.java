package fr.efreicraft.eclobby.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import static fr.efreicraft.eclobby.Main.INSTANCE;

public class AdminPortal implements Listener {
    @EventHandler
    public void onGoToPortal(PlayerMoveEvent event) {
        Location loc = event.getPlayer().getLocation();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        float yaw = loc.getYaw();
        float pitch = loc.getPitch();
        Vector velocity = event.getPlayer().getVelocity().clone();
        if (x >= -42 && x <= -41 && y >= 52.0 && y <= 57.0 && z >= -0.7 && z <= 1.7) {
            if (yaw >= 0 && yaw <= 180) {
                yaw -= 180;
            }
            event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), -12.5, y-1, 0.5, yaw, pitch));
            INSTANCE.getServer().getScheduler().runTaskLater(INSTANCE, () -> event.getPlayer().setVelocity(velocity), 1L);
        }
    }
}
