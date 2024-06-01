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

package omg.alcoloid.horizontalcompass.waypoint;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public final class HomeWaypoint extends LocationWaypoint {
    private final String name;

    public HomeWaypoint(@NotNull Location position, @NotNull String name, char markerSymbol) {
        super(position, markerSymbol);

        this.name = name;
    }

    public String getHomeName() {
        return name;
    }
}
