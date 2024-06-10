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

import com.github.alcoloid0.horizontalcompass.api.util.Identifier;
import com.github.alcoloid0.horizontalcompass.api.waypoint.Waypoint;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public final class WaypointImpl implements Waypoint {
    private final Identifier identifier;
    private Location location;
    private String label;
    private TextColor color;

    public WaypointImpl(@NotNull Identifier identifier,
                        @NotNull Location location,
                        @NotNull String label,
                        @NotNull TextColor color) {

        this.identifier = identifier;
        this.location = location;
        this.label = label;
        this.color = color;
    }

    public @NotNull Identifier getIdentifier() {
        return this.identifier;
    }

    public @NotNull TextColor getTextColor() {
        return this.color;
    }

    @Override
    public void setTextColor(@NotNull TextColor color) {
        this.color = color;
    }

    public @NotNull String getLabel() {
        return this.label;
    }

    @Override
    public void setLabel(@NotNull String label) {
        this.label = label;
    }

    public @NotNull Location getLocation() {
        return this.location;
    }

    @Override
    public void updateLocation(@NotNull Location location) {
        this.location = location;
    }
}