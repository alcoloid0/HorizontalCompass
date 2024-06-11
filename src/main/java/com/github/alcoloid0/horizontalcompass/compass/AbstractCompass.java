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
import com.github.alcoloid0.horizontalcompass.compass.display.CompassDisplay;
import com.github.alcoloid0.horizontalcompass.util.CardinalDirection;
import com.github.alcoloid0.horizontalcompass.util.PlayerLookUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public abstract class AbstractCompass implements Compass {
    protected final Player player;
    protected final CompassDisplay display;
    private final CompassWaypoints waypoints;

    protected AbstractCompass(@NotNull Player player, @NotNull CompassDisplay display) {
        this.player = player;
        this.display = display;
        this.waypoints = new CompassWaypointsImpl(this);
    }

    @Override
    public void update() {
        this.display.flush();

        Location location = this.player.getLocation();

        int lookAngle = PlayerLookUtil.lookAngle(this.player);
        int angleCount = this.display.getAngleCount();

        for (int angle : PlayerLookUtil.lookAngleOffsets(this.player, angleCount)) {
            Waypoint waypoint = this.waypoints.getByAngleBetween(location, angle)
                    .orElse(null);

            if (waypoint != null) {
                this.display.append(angle, waypoint);
                continue;
            }

            CardinalDirection direction = Arrays.stream(CardinalDirection.values())
                    .filter(dir -> dir.getRotationAngle() == angle)
                    .findFirst()
                    .orElse(null);

            if (direction != null) {
                this.display.append(angle, direction);
                continue;
            }

            this.display.append(angle, angle == lookAngle);
        }
    }

    @Override
    public @NotNull CompassWaypoints getWaypoints() {
        return this.waypoints;
    }

    @Override
    public @NotNull Player getCompassPlayer() {
        return this.player;
    }
}