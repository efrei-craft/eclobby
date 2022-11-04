package fr.efreicraft.eclobby.listeners;

import fr.efreicraft.eclobby.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.util.Vector;

import static fr.efreicraft.eclobby.Main.INSTANCE;

public class AdminPortal implements Listener {

    @EventHandler
    public void onGoToPortal(PlayerMoveEvent event) {
        World overworld = event.getPlayer().getWorld();
        if (overworld.getEnvironment() != World.Environment.NORMAL) return;

        Location loc = event.getPlayer().getLocation();
        float yaw = loc.getYaw();
        float pitch = loc.getPitch();
        Vector vel = event.getPlayer().getVelocity();

        // Aller-retour salle admin.
        if (Utils.isBlockInside(loc, new Location(overworld, -41, 52, -1), 0, 5, 2)) {
            if (Utils.inRange(yaw, 0, 180)) yaw -= 180;

            event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), -12, 51, 0.5, yaw, pitch));
            INSTANCE.getServer().getScheduler().runTaskLaterAsynchronously(INSTANCE, () -> event.getPlayer().setVelocity(vel), 1L);
        } else if (Utils.isBlockInside(loc, new Location(overworld, -13, 51, -1), 0, 5, 2)) {
            if (Utils.inRange(yaw, -180, 0)) yaw += 180;

            event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), -42, 52, 0.5, yaw, pitch));
            INSTANCE.getServer().getScheduler().runTaskLater(INSTANCE, () -> event.getPlayer().setVelocity(vel), 1L);
        }

        // Aller-retour pôle nether.
        else if (Utils.isBlockInside(loc, new Location(overworld, -1, -3, -12), 2, 2, 0)) {
            yaw = -90;

            event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), -47, -1, -47.5, yaw, pitch));
            INSTANCE.getServer().getScheduler().runTaskLaterAsynchronously(INSTANCE, () -> event.getPlayer().setVelocity(vel), 1L);
        } else if (Utils.isBlockInside(loc, new Location(overworld, -48, -1, -48), 0, 2, 2)) {
            yaw = 0;

            event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), 0.5, -3, -11, yaw, pitch));
            INSTANCE.getServer().getScheduler().runTaskLater(INSTANCE, () -> event.getPlayer().setVelocity(vel), 1L);
        }
    }
}
