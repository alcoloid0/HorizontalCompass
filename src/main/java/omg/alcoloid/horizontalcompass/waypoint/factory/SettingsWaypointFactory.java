package omg.alcoloid.horizontalcompass.waypoint.factory;

import net.kyori.adventure.text.format.TextColor;
import omg.alcoloid.horizontalcompass.settings.Settings;
import omg.alcoloid.horizontalcompass.util.CardinalDirection;
import omg.alcoloid.horizontalcompass.waypoint.CardinalWaypoint;
import omg.alcoloid.horizontalcompass.waypoint.HomeWaypoint;
import omg.alcoloid.horizontalcompass.waypoint.LocationWaypoint;
import omg.alcoloid.horizontalcompass.waypoint.Waypoint;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public final class SettingsWaypointFactory implements WaypointFactory {
    private final Settings settings;

    public SettingsWaypointFactory(@NotNull Settings settings) {
        this.settings = settings;
    }

    @Override
    public @NotNull Waypoint createHomeWaypoint(@NotNull Location location, @NotNull String name) {
        return new HomeWaypoint(location, name, this.settings.getHomeMarker());
    }

    @Override
    public @NotNull Waypoint createLocationWaypoint(@NotNull Location location) {
        return new LocationWaypoint(location, this.settings.getLocationMarker());
    }

    @Override
    public @NotNull List<Waypoint> getCardinalWaypointList() {
        TextColor color = this.settings.getCardinalColor();

        return Arrays.stream(CardinalDirection.values())
                .map(direction -> (Waypoint) new CardinalWaypoint(direction, color))
                .toList();
    }
}
