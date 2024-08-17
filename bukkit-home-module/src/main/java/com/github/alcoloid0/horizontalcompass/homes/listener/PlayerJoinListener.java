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
import com.github.alcoloid0.horizontalcompass.homes.util.EssentialsHomes;
import com.github.alcoloid0.horizontalcompass.homes.waypoint.HomeWaypointIdentifier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public final class PlayerJoinListener implements Listener {
    private final HorizontalCompassAPI compassAPI = HorizontalCompassHomes.getCompassAPI();

    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        Compass compass = compassAPI.getCompassByPlayer(event.getPlayer()).orElseThrow(RuntimeException::new);

        EssentialsHomes.getHomes(event.getPlayer()).forEach((name, location) -> {
            Waypoint waypoint = compassAPI.newWaypointBuilder(location)
                    .identifier(HomeWaypointIdentifier.at(event.getPlayer(), name))
                    .label(name)
                    .build();

            compass.getWaypoints().add(waypoint);
        });

        compass.update();
    }
}
