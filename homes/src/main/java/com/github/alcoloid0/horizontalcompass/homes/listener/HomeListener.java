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

package com.github.alcoloid0.horizontalcompass.homes.listener;

import com.github.alcoloid0.horizontalcompass.api.HorizontalCompassAPI;
import com.github.alcoloid0.horizontalcompass.api.compass.Compass;
import com.github.alcoloid0.horizontalcompass.api.waypoint.Waypoint;
import com.github.alcoloid0.horizontalcompass.homes.HorizontalCompassHomes;
import com.github.alcoloid0.horizontalcompass.homes.util.CompassUser;
import com.github.alcoloid0.horizontalcompass.homes.waypoint.HomeWaypointIdentifier;
import net.essentialsx.api.v2.events.HomeModifyEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public final class HomeListener implements Listener {
    private final HorizontalCompassAPI compassAPI = HorizontalCompassHomes.getCompassAPI();

    @EventHandler
    public void onHomeModify(@NotNull HomeModifyEvent event) {
        CompassUser user = CompassUser.wrap(event.getHomeOwner());

        Compass compass = user.getCompass().orElseThrow();

        switch (event.getCause()) {
            case CREATE -> {
                Waypoint waypoint = compassAPI.newWaypointBuilder(event.getNewLocation())
                        .identifier(HomeWaypointIdentifier.at(user, event.getNewName()))
                        .label(event.getNewName())
                        .build();

                compass.getWaypoints().add(waypoint);
            }
            case DELETE -> {
                compass.getWaypoints().remove(HomeWaypointIdentifier.at(user, event.getOldName()));
            }
            case RENAME -> {
                Waypoint waypoint = compass.getWaypoints()
                        .get(HomeWaypointIdentifier.at(user, event.getOldName()))
                        .get(0);

                waypoint.setLabel(event.getNewName());

                if (waypoint.getIdentifier() instanceof HomeWaypointIdentifier identifier) {
                    identifier.setHomeName(event.getNewName());
                }
            }
            case UPDATE -> {
                Waypoint waypoint = compass.getWaypoints()
                        .get(HomeWaypointIdentifier.at(user, event.getNewName()))
                        .get(0);

                waypoint.updateLocation(event.getNewLocation());
            }
        }

        compass.update();
    }
}
