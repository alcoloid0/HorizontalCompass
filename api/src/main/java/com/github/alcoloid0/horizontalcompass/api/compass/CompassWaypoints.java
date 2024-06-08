package com.github.alcoloid0.horizontalcompass.api.compass;

import com.github.alcoloid0.horizontalcompass.api.waypoint.Waypoint;
import com.github.alcoloid0.horizontalcompass.api.waypoint.WaypointIdentifier;
import org.jetbrains.annotations.NotNull;

public interface CompassWaypoints extends Iterable<Waypoint> {
    void add(@NotNull Waypoint waypoint);

    void remove(@NotNull Waypoint waypoint);

    void remove(@NotNull WaypointIdentifier identifier);
}
