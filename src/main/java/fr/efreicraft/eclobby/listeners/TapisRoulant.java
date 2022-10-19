package fr.efreicraft.eclobby.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TapisRoulant implements Listener {

    private static final double speedMultiplier = 0.4;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Material blockAtPlayer = event.getPlayer().getLocation().getBlock().getType();
        if (blockAtPlayer == Material.BLACK_CARPET) {
            Material blockUnderPlayer = event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType();
            if (blockUnderPlayer == Material.RED_GLAZED_TERRACOTTA) {
                event.getPlayer().setVelocity(new Location(event.getPlayer().getWorld(), 0, 0, 0, 180, 0).getDirection().multiply(speedMultiplier));
            }
            else if (blockUnderPlayer == Material.YELLOW_GLAZED_TERRACOTTA) {
                event.getPlayer().setVelocity(new Location(event.getPlayer().getWorld(), 0, 0, 0, 0, 0).getDirection().multiply(speedMultiplier));
            }
            else if (blockUnderPlayer == Material.GREEN_GLAZED_TERRACOTTA) {
                event.getPlayer().setVelocity(new Location(event.getPlayer().getWorld(), 0, 0, 0, 90, 0).getDirection().multiply(speedMultiplier));
            }
            else if (blockUnderPlayer == Material.BLUE_GLAZED_TERRACOTTA) {
                event.getPlayer().setVelocity(new Location(event.getPlayer().getWorld(), 0, 0, 0, -90, 0).getDirection().multiply(speedMultiplier));
            }
        }
    }
}
