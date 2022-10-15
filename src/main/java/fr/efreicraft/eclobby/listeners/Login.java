package fr.efreicraft.eclobby.listeners;

import fr.efreicraft.eclobby.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Login implements Listener {
    public Login() {
        Main.INSTANCE.getServer().getPluginManager().registerEvents(this, Main.INSTANCE);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("eclobby.fly")) {
            player.setAllowFlight(true);
        }
    }
}
