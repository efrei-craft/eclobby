package fr.efreicraft.eclobby.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;

/**
 * Classe générique utilitaire
 */
public class Utils {

    /**
     * @param location the location that is checked
     * @param corner1 one of the corner of the bounding box created by corner1 and corner2
     * @param corner2 the other one of the corner of the bounding box created by corner1 and corner2
     * @return {@code true} if location is inside the bounding box
     */
    public static boolean isInside(Location location, Location corner1, Location corner2) {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();

        return (inRange(x, corner1.getX(), corner2.getX())) &&
                (inRange(y, corner1.getY(), corner2.getY())) &&
                 (inRange(z, corner1.getZ(), corner2.getZ()));
    }

    // Dites moi dans quel monde ça existe pas cette fonction
    public static boolean inRange(double x, double a, double b) {
        return Math.min(a,b) <= x
                && Math.max(a,b) >= x;
    }

    public static String colorize(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
