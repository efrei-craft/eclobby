package fr.efreicraft.eclobby.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InventoryClick implements Listener {
    @EventHandler
    public void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent event) {
        if (event.getWhoClicked().getGameMode().equals(GameMode.ADVENTURE)) {
            if (!event.getView().getTitle().equals("Menu")) {
                event.setCancelled(true);
            }
        }
    }
}
