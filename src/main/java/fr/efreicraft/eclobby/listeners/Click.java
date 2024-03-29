package fr.efreicraft.eclobby.listeners;

import fr.efreicraft.eclobby.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Click implements Listener {

    // D'où à où s'étendent les banrooms?
    final Location corner1 = new Location(Bukkit.getServer().getWorld("world"),32, 300, 100);
    final Location corner2 = new Location(Bukkit.getServer().getWorld("world"), -32, 210, -100);

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.ADVENTURE) {
            if (event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType().name().contains("_PLATE")) {
                if (Utils.isInside(event.getPlayer().getLocation(), corner1, corner2))
                    event.setCancelled(false);
            } else
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityClick(PlayerInteractEntityEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.ADVENTURE) {
            // Cancel all click events
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player) {
            if (player.getGameMode() == GameMode.ADVENTURE) {
                // Cancel all click events
                event.setCancelled(true);
            }
        }
    }
}
