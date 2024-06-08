package com.github.alcoloid0.horizontalcompass.api.waypoint;

import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

public interface WaypointBuilder {
    @NotNull
    Waypoint color(@NotNull TextColor color);

    @NotNull
    Waypoint label(@NotNull String label);

    @NotNull
    Waypoint marker(char markerSymbol);

    @NotNull
    Waypoint identifier(@NotNull WaypointIdentifier identifier);

    @NotNull
    Waypoint build();
}
