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

package com.github.alcoloid0.horizontalcompass.api.compass;

import com.github.alcoloid0.horizontalcompass.api.waypoint.Waypoint;
import com.github.alcoloid0.horizontalcompass.api.waypoint.WaypointIdentifier;
import org.bukkit.Location;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CompassWaypoints extends Collection<Waypoint> {
    void remove(@NotNull WaypointIdentifier identifier);

    @NotNull
    List<Waypoint> get(@NotNull WaypointIdentifier identifier);

    @NotNull
    Compass getCompass();

    @Deprecated(since = "1.1.1", forRemoval = true)
    @ApiStatus.Internal
    @NotNull
    default Optional<Waypoint> getByAngleBetween(@NotNull Location location, int angle) {
        throw new RuntimeException("deprecated");
    }
}
