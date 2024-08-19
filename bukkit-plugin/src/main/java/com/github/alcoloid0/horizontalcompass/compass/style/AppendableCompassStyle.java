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

package com.github.alcoloid0.horizontalcompass.compass.style;

import com.github.alcoloid0.horizontalcompass.api.compass.Compass;
import com.github.alcoloid0.horizontalcompass.api.waypoint.Waypoint;
import com.github.alcoloid0.horizontalcompass.util.Direction;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AppendableCompassStyle implements CompassStyle {
    protected TextComponent.Builder builder;

    protected AppendableCompassStyle() {
        this.flush();
    }

    protected abstract void append(int angle, boolean center);

    protected abstract void append(int angle, @NotNull Waypoint waypoint);

    protected abstract void append(int angle, @NotNull Direction direction);

    protected abstract int angleCount();

    @Override
    public void update(@NotNull Compass forCompass) {
        this.flush();

        Location location = forCompass.getCompassPlayer().getLocation();

        int lookAngle360 = Math.round(forCompass.getCompassPlayer().getEyeLocation().getYaw()) + 180;

        int halfAngleCount = this.angleCount() / 2;

        for (int offset = -halfAngleCount; offset < (halfAngleCount + 1); offset++) {
            int angle = Math.floorMod(lookAngle360 + offset, 360);

            Waypoint waypoint = getWaypointByAngleBetween(forCompass, location, angle);
            Direction direction = Direction.byAzimuth(angle);

            if (waypoint != null) {
                this.append(angle, waypoint);
            } else if (direction != null) {
                this.append(angle, direction);
            } else {
                this.append(angle, angle == lookAngle360);
            }
        }
    }

    private @Nullable Waypoint getWaypointByAngleBetween(@NotNull Compass compass,
                                                         @NotNull Location location,
                                                         int angle) {

        for (Waypoint waypoint : compass.getWaypoints()) {
            Vector difference = location.toVector().subtract(waypoint.getLocation().toVector());

            double theta = Math.atan2(difference.getZ(), difference.getX()) - (Math.PI / 2.0);
            long angleBetween = Math.floorMod(Math.round(Math.toDegrees(theta)), 360);

            if (angleBetween == angle) {
                return waypoint;
            }
        }

        return null;
    }

    @Override
    public @NotNull Component getComponent() {
        return this.builder.build();
    }

    protected @NotNull Component format(@NotNull String format, @NotNull Object... args) {
        return Component.text(String.format(format, args));
    }

    protected void append(@NotNull ComponentLike component) {
        this.builder.append(component);
    }

    protected void appendSpace() {
        this.builder.appendSpace();
    }

    private void flush() {
        this.builder = Component.text();
    }
}
