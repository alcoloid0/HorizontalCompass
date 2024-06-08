package com.github.alcoloid0.horizontalcompass.api.waypoint;

import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

public interface WaypointBuilder {
    @NotNull
    WaypointBuilder color(@NotNull TextColor color);

    @NotNull
    WaypointBuilder label(@NotNull String label);

    @NotNull
    WaypointBuilder marker(char markerSymbol);

    @NotNull
    WaypointBuilder identifier(@NotNull WaypointIdentifier identifier);

    @NotNull
    Waypoint build();
}
