package omg.alcoloid.horizontalcompass.waypoint;

import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface Waypoint {
    int getRotationAngle(@NotNull Location location);

    @NotNull
    TextColor getTextColor();

    char getMarkerSymbol();
}