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

package com.github.alcoloid0.horizontalcompass.homes.waypoint;

import com.github.alcoloid0.horizontalcompass.api.waypoint.WaypointIdentifier;
import com.github.alcoloid0.horizontalcompass.homes.util.CompassUser;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class HomeWaypointIdentifier implements WaypointIdentifier {
    private final Player player;
    private String homeName;

    public HomeWaypointIdentifier(@NotNull Player player, @NotNull String homeName) {
        this.player = player;
        this.homeName = homeName;
    }

    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull WaypointIdentifier at(@NotNull Player player, @NotNull String homeName) {
        return new HomeWaypointIdentifier(player, homeName);
    }

    public static @NotNull WaypointIdentifier at(@NotNull CompassUser user, @NotNull String homeName) {
        return HomeWaypointIdentifier.at(user.getPlayer(), homeName);
    }

    public void setHomeName(@NotNull String homeName) {
        this.homeName = homeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeWaypointIdentifier that = (HomeWaypointIdentifier) o;
        return Objects.equals(player, that.player) && Objects.equals(homeName, that.homeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, homeName);
    }
}
