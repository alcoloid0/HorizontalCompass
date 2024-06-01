package omg.alcoloid.horizontalcompass.waypoint.factory;

import omg.alcoloid.horizontalcompass.waypoint.Waypoint;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface WaypointFactory {
    @NotNull
    Waypoint createHomeWaypoint(@NotNull Location location, @NotNull String name);

    @NotNull
    Waypoint createLocationWaypoint(@NotNull Location location);

    @NotNull
    List<Waypoint> getCardinalWaypointList();
}
