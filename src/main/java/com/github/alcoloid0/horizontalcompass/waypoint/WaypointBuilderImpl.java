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
import com.github.alcoloid0.horizontalcompass.api.waypoint.WaypointBuilder;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class WaypointBuilderImpl implements WaypointBuilder {
    private final Location location;
    private TextColor color;
    private String label;
    private Identifier identifier;

    public WaypointBuilderImpl(@NotNull Location location) {
        this.location = location;
        this.color = TextColor.color(ThreadLocalRandom.current().nextInt(0xFFFFFF));
        this.label = "";
        this.identifier = Identifier.uuid(UUID.randomUUID());
    }

    @Override
    public @NotNull WaypointBuilder color(@NotNull TextColor color) {
        this.color = color;
        return this;
    }

    @Override
    public @NotNull WaypointBuilder label(@NotNull String label) {
        this.label = label;
        return this;
    }

    @Override
    public @NotNull WaypointBuilder identifier(@NotNull Identifier identifier) {
        this.identifier = identifier;
        return this;
    }

    @Override
    public @NotNull Waypoint build() {
        return new WaypointImpl(this.identifier, this.location, this.label, this.color);
    }
}
