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

package com.github.alcoloid0.horizontalcompass.display;

import com.github.alcoloid0.horizontalcompass.api.compass.Compass;
import com.github.alcoloid0.horizontalcompass.api.waypoint.Waypoint;
import com.github.alcoloid0.horizontalcompass.util.CardinalDirection;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public abstract class AppendableCompassDisplay implements CompassDisplay {
    protected TextComponent.Builder builder;

    public AppendableCompassDisplay() {
        this.flush();
    }

    protected abstract void append(int angle, boolean center);

    protected abstract void append(int angle, @NotNull Waypoint waypoint);

    protected abstract void append(int angle, @NotNull CardinalDirection direction);

    protected abstract int angleCount();

    @Override
    public void update(@NotNull Compass forCompass) {
        this.flush();

        Location location = forCompass.getCompassPlayer().getLocation();

        int lookAngle = Math.round(forCompass.getCompassPlayer().getEyeLocation().getYaw()) + 180;

        int halfAngleCount = this.angleCount() / 2;

        for (int offset = -halfAngleCount; offset < halfAngleCount; offset++) {
            int angle = Math.floorMod(lookAngle + offset, 360);

            Waypoint waypoint = forCompass.getWaypoints().getByAngleBetween(location, angle)
                    .orElse(null);

            if (waypoint != null) {
                this.append(angle, waypoint);
                continue;
            }

            CardinalDirection direction = Arrays.stream(CardinalDirection.values())
                    .filter(dir -> dir.getRotationAngle() == angle)
                    .findFirst()
                    .orElse(null);

            if (direction != null) {
                this.append(angle, direction);
                continue;
            }

            this.append(angle, angle == lookAngle);
        }
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
