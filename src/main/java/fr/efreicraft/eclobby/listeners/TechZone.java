package fr.efreicraft.eclobby.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TechZone implements Listener {
    @EventHandler
    public void onPlayerWalkOnBlock(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location loc = player.getLocation();
        Location blockUnderPlayer = new Location(loc.getWorld(), loc.getX(), loc.getY() - 1, loc.getZ());
        if (blockUnderPlayer.getBlock().getType() == Material.JIGSAW) {
            float playerYaw = loc.getYaw();
            if (playerYaw > -45 && playerYaw < 45) { // player looking at south - Z+
                player.setVelocity((new Location(player.getWorld(), loc.getX(), loc.getY(), loc.getZ(), 0, -30)).getDirection());
            }
            else if (playerYaw > 45 && playerYaw < 135) { // player looking at east - X+
                player.setVelocity((new Location(player.getWorld(), loc.getX(), loc.getY(), loc.getZ(), 90, -30)).getDirection());
            }
            else if (playerYaw > 135 || playerYaw < -135) { // player looking at north - Z-
                player.setVelocity((new Location(player.getWorld(), loc.getX(), loc.getY(), loc.getZ(), 180, -30)).getDirection());
            }
            else { // player looking at west - X-
                player.setVelocity((new Location(player.getWorld(), loc.getX(), loc.getY(), loc.getZ(), -90, -30)).getDirection());
            }
        }
    }
}
