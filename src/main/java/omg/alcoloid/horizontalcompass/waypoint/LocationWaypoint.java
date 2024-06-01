package omg.alcoloid.horizontalcompass.waypoint;

import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class LocationWaypoint implements Waypoint {
    private final Location position;
    private final TextColor color;
    private final char marker;

    public LocationWaypoint(@NotNull Location position, char markerSymbol) {
        this.position = position;
        this.color = this.randomTextColor();
        this.marker = markerSymbol;
    }

    @Override
    public int getRotationAngle(@NotNull Location location) {
        Vector vec = location.toVector().subtract(this.position.toVector());
        double theta = Math.atan2(vec.getZ(), vec.getX()) - (Math.PI / 2.0);
        return Math.floorMod(Math.round(Math.toDegrees(theta)), 360);
    }

    @Override
    public @NotNull TextColor getTextColor() {
        return this.color;
    }

    @Override
    public char getMarkerSymbol() {
        return this.marker;
    }

    private @NotNull TextColor randomTextColor() {
        return TextColor.color(ThreadLocalRandom.current().nextInt(0xFFFFFF));
    }
}
