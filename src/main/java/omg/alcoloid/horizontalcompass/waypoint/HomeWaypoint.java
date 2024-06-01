package omg.alcoloid.horizontalcompass.waypoint;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public final class HomeWaypoint extends LocationWaypoint {
    private final String name;

    public HomeWaypoint(@NotNull Location position, @NotNull String name, char markerSymbol) {
        super(position, markerSymbol);

        this.name = name;
    }

    public String getHomeName() {
        return name;
    }
}
