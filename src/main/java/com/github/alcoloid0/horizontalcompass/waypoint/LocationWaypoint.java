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

package com.github.alcoloid0.horizontalcompass.waypoint;

import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class LocationWaypoint implements Waypoint {
    private final Location position;
    private final TextColor color;
    private final char marker;

    public LocationWaypoint(@NotNull Location position, char markerSymbol) {
        this.position = position;
        this.color = this.randomTextColor();
        this.marker = markerSymbol;
    }

    @Override
    public int getRotationAngle(@NotNull Location location) {
        Vector vec = location.toVector().subtract(this.position.toVector());
        double theta = Math.atan2(vec.getZ(), vec.getX()) - (Math.PI / 2.0);
        return Math.floorMod(Math.round(Math.toDegrees(theta)), 360);
    }

    @Override
    public @NotNull TextColor getTextColor() {
        return this.color;
    }

    @Override
    public char getMarkerSymbol() {
        return this.marker;
    }

    private @NotNull TextColor randomTextColor() {
        return TextColor.color(ThreadLocalRandom.current().nextInt(0xFFFFFF));
    }
}
