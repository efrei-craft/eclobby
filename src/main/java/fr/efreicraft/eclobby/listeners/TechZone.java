package fr.efreicraft.eclobby.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;

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

    // Applied Energistics System
    public Inventory ae_storage = Bukkit.createInventory(null, 54, Component.text("Applied Energistics System"));

    @EventHandler
    public void onRightClickOnAEItemFrame(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Location itemFrameLoc = event.getRightClicked().getLocation();
        if (itemFrameLoc.getX() == -40.5 && itemFrameLoc.getY() == 0.5 && itemFrameLoc.getZ() == -4.96875) {
            player.openInventory(ae_storage);
        }
    }

    @EventHandler
    public void onJump(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        float playerYaw = player.getLocation().getYaw();
        float playerPitch = player.getLocation().getPitch();
        Location blockUnderPlayerLoc = new Location(player.getWorld(), event.getFrom().getX(), event.getFrom().getY() - 1, event.getFrom().getZ());
        Material blockUnderPlayer = blockUnderPlayerLoc.getBlock().getType();
        if (event.getTo().getY() > event.getFrom().getY()) {
            if (blockUnderPlayer == Material.CALCITE) {
                // loop all blocks between player and 10 blocks above player
                for (int i = 1; i <= 10; i++) {
                    Location loc = new Location(player.getWorld(), event.getFrom().getX(), event.getFrom().getY() + i, event.getFrom().getZ(), playerYaw, playerPitch);
                    Material block = loc.getBlock().getType();
                    if (block == Material.CALCITE) {
                        player.teleport(new Location(player.getWorld(), loc.getX(), loc.getY() + 1, loc.getZ(), playerYaw, playerPitch));
                        break;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        float playerYaw = player.getLocation().getYaw();
        float playerPitch = player.getLocation().getPitch();
        Location blockUnderPlayerLoc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() - 1, player.getLocation().getZ());
        Material blockUnderPlayer = blockUnderPlayerLoc.getBlock().getType();
        if (player.isSneaking()) {
            if (blockUnderPlayer == Material.CALCITE) {
                for (int i = -2; i >= -11; i--) {
                    Location loc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + i, player.getLocation().getZ(), playerYaw, playerPitch);
                    Material block = loc.getBlock().getType();
                    if (block == Material.CALCITE) {
                        player.teleport(new Location(player.getWorld(), loc.getX(), loc.getY() + 1, loc.getZ(), playerYaw, playerPitch));
                        break;
                    }
                }
            }
        }
    }
}
