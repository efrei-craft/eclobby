package fr.efreicraft.eclobby.fields;

import fr.efreicraft.animus.endpoints.GameService;
import fr.efreicraft.animus.endpoints.PlayerService;
import fr.efreicraft.animus.invoker.ApiException;
import fr.efreicraft.animus.models.Game;
import fr.efreicraft.eclobby.ECLobby;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.Map;

public class AvailableGames {

    public static List<Game> games;

    public static Map<String, Integer> playerCountPerGame;

    public static void startRefreshing() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(ECLobby.getInstance(), () -> {
            try {
                games = GameService.getAllGames();
                playerCountPerGame = GameService.getPlayersCountMap();
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        }, 0, 20 * 10);
    }

}
