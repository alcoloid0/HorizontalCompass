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

package omg.alcoloid.horizontalcompass.listener;

import net.essentialsx.api.v2.events.HomeModifyEvent;
import omg.alcoloid.horizontalcompass.HorizontalCompass;
import omg.alcoloid.horizontalcompass.compass.Compass;
import omg.alcoloid.horizontalcompass.waypoint.HomeWaypoint;
import omg.alcoloid.horizontalcompass.waypoint.Waypoint;
import omg.alcoloid.horizontalcompass.waypoint.factory.WaypointFactory;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class HomeListener implements Listener {
    private final HorizontalCompass compassPlugin;

    public HomeListener(@NotNull HorizontalCompass compassPlugin) {
        this.compassPlugin = compassPlugin;
    }

    @EventHandler
    public void onHomeModify(@NotNull HomeModifyEvent event) {
        Player player = event.getHomeOwner().getBase();

        if (this.compassPlugin.getSettings().isEssentialsHomeDisabled()) {
            return;
        }

        Compass compass = this.compassPlugin.getPlayerCompassMap().get(player);

        switch (event.getCause()) {
            case DELETE -> this.onHomeDelete(compass, event.getOldName());
            case CREATE -> this.onHomeCreate(compass, event.getNewLocation(), event.getNewName());
        }
    }

    private void onHomeCreate(@NotNull Compass compass, @NotNull Location location, @NotNull String name) {
        WaypointFactory factory = this.compassPlugin.getWaypointFactory();

        compass.getWaypoints().add(factory.createHomeWaypoint(location, name));
    }

    private void onHomeDelete(@NotNull Compass compass, @NotNull String name) {
        compass.getWaypoints().removeIf(this.deleteFilter(name));
    }

    @Contract(pure = true)
    private @NotNull Predicate<Waypoint> deleteFilter(@NotNull String homeName) {
        return waypoint -> {
            if (waypoint instanceof HomeWaypoint homeWaypoint) {
                return homeWaypoint.getHomeName().equals(homeName);
            }

            return false;
        };
    }
}
