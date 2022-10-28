package fr.efreicraft.eclobby.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class HUDManager {
    public static void setScoreboard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();

        final Scoreboard board = manager.getNewScoreboard();
        final Objective objective = board.registerNewObjective("efreicraft-" + player.getName(), Criteria.DUMMY,
                Component.text("      §e§lEFREI CRAFT     "));
        objective.setDisplaySlot(org.bukkit.scoreboard.DisplaySlot.SIDEBAR);
        Score score10 = objective.getScore("§r          ");
        score10.setScore(9);
        Score score9 = objective.getScore("§7Votre rôle : " + player.getDisplayName().split("§l")[0].split("\\[")[0] + player.getDisplayName().split("\\[")[1].split("]")[0]);
        score9.setScore(8);
        Score score8 = objective.getScore("§r         ");
        score8.setScore(7);
        Score score7 = objective.getScore("§7En ligne: §a" + Bukkit.getOnlinePlayers().size());
        score7.setScore(6);
        Score score6 = objective.getScore("§r      ");
        score6.setScore(5);
        Score score1 = objective.getScore("§cefreicraft.fr");
        score1.setScore(0);
        player.setScoreboard(board);
    }
}
