/*
 * Copyright (C) 2024 alcoloid (alcoloid0)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.alcoloid0.horizontalcompass.waypoint.factory;

import com.github.alcoloid0.horizontalcompass.waypoint.HomeWaypoint;
import net.kyori.adventure.text.format.TextColor;
import com.github.alcoloid0.horizontalcompass.settings.Settings;
import com.github.alcoloid0.horizontalcompass.util.CardinalDirection;
import com.github.alcoloid0.horizontalcompass.waypoint.CardinalWaypoint;
import com.github.alcoloid0.horizontalcompass.waypoint.LocationWaypoint;
import com.github.alcoloid0.horizontalcompass.waypoint.Waypoint;
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
