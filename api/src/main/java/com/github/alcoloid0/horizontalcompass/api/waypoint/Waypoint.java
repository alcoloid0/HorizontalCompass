package com.github.alcoloid0.horizontalcompass.api.waypoint;

import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface Waypoint {
    @NotNull
    WaypointIdentifier getIdentifier();

    @NotNull
    TextColor getTextColor();

    @NotNull
    String getLabel();

    char getMarkerSymbol();

    @NotNull
    Location getLocation();

    int getRotationAngle(@NotNull Location location);
}