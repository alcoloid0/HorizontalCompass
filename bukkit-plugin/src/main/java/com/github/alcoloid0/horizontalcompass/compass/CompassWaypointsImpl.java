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

package com.github.alcoloid0.horizontalcompass.compass;

import com.github.alcoloid0.horizontalcompass.api.compass.Compass;
import com.github.alcoloid0.horizontalcompass.api.compass.CompassWaypoints;
import com.github.alcoloid0.horizontalcompass.api.waypoint.Waypoint;
import com.github.alcoloid0.horizontalcompass.api.waypoint.WaypointIdentifier;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public final class CompassWaypointsImpl extends ArrayList<Waypoint> implements CompassWaypoints {
    private final transient Compass compass;
    private final transient List<Waypoint> waypoints;

    public CompassWaypointsImpl(@NotNull Compass compass) {
        this.compass = compass;
        this.waypoints = new ArrayList<>();
    }

    @Override
    public void remove(@NotNull WaypointIdentifier identifier) {
        this.waypoints.removeIf(waypoint -> waypoint.getIdentifier().equals(identifier));
    }

    @Override
    public @NotNull List<Waypoint> get(@NotNull WaypointIdentifier identifier) {
        return this.waypoints.stream()
                .filter(waypoint -> waypoint.getIdentifier().equals(identifier))
                .collect(Collectors.toList());
    }

    @Override
    public @NotNull Compass getCompass() {
        return this.compass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CompassWaypointsImpl other = (CompassWaypointsImpl) o;
        return Objects.equals(compass, other.compass) && Objects.equals(waypoints, other.waypoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), compass, waypoints);
    }
}
