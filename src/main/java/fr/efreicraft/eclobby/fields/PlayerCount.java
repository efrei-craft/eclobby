package fr.efreicraft.eclobby.fields;

import fr.efreicraft.animus.endpoints.PlayerService;
import fr.efreicraft.animus.invoker.ApiException;
import fr.efreicraft.eclobby.ECLobby;
import org.bukkit.Bukkit;

public class PlayerCount {

    public static int playerCount = 0;

    public static void startRefreshing() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(ECLobby.getInstance(), () -> {
            try {
                playerCount = PlayerService.getOnlinePlayers().size();
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        }, 0, 20 * 5);
    }

}
