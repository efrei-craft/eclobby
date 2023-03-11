package fr.efreicraft.eclobby.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class InventoryAction implements Listener {
    @EventHandler
    public void onInventoryDrop(PlayerDropItemEvent event) {
        if (event.getPlayer().getGameMode().equals(GameMode.ADVENTURE)) {
            event.setCancelled(true);
        }
    }
}
