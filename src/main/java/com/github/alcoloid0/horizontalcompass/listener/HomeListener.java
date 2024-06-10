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

package com.github.alcoloid0.horizontalcompass.listener;

import com.github.alcoloid0.horizontalcompass.HorizontalCompass;
import com.github.alcoloid0.horizontalcompass.api.compass.Compass;
import com.github.alcoloid0.horizontalcompass.api.util.Identifier;
import com.github.alcoloid0.horizontalcompass.api.waypoint.Waypoint;
import com.github.alcoloid0.horizontalcompass.hook.HookManager;
import net.essentialsx.api.v2.events.HomeModifyEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public final class HomeListener implements Listener {
    private final Identifier identifier;
    private final HorizontalCompass compassPlugin;

    public HomeListener(@NotNull HorizontalCompass compassPlugin) {
        this.identifier = Identifier.plugin(compassPlugin, "home");
        this.compassPlugin = compassPlugin;
    }

    @EventHandler
    public void onHomeModify(@NotNull HomeModifyEvent event) {
        Player player = event.getHomeOwner().getBase();

        if (this.compassPlugin.getSettings().isEssentialsHomeDisabled()) {
            return;
        }

        this.compassPlugin.getCompassByPlayer(player).ifPresent(compass -> {
            switch (event.getCause()) {
                case DELETE -> this.onHomeDelete(compass, event.getOldName());
                case CREATE -> this.onHomeCreate(compass, event.getNewLocation(), event.getNewName());
            }
        });
    }

    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        this.compassPlugin.getCompassByPlayer(event.getPlayer()).ifPresent(compass -> {
            if (this.compassPlugin.getSettings().isEssentialsHomeDisabled()) {
                return;
            }

            Player player = event.getPlayer();

            HookManager hookManager = this.compassPlugin.getHookManager();

            hookManager.essentials().ifPresent(essentials -> {
                for (String homeName : essentials.getPlayerHomeList(player)) {
                    Location homeLocation = essentials.getPlayerHome(player, homeName);
                    compass.getWaypoints().add(this.createHomeWaypoint(homeLocation, homeName));
                }
            });
        });
    }

    private void onHomeCreate(@NotNull Compass compass, @NotNull Location location, @NotNull String name) {
        compass.getWaypoints().add(this.createHomeWaypoint(location, name));
    }

    private void onHomeDelete(@NotNull Compass compass, @NotNull String name) {
        compass.getWaypoints().removeIf(this.deleteFilter(name));
    }

    @Contract(pure = true)
    private @NotNull Predicate<Waypoint> deleteFilter(@NotNull String homeName) {
        return waypoint -> {
            if (waypoint.getIdentifier().equals(this.identifier)) {
                return waypoint.getLabel().equals(homeName);
            }

            return false;
        };
    }

    private @NotNull Waypoint createHomeWaypoint(@NotNull Location location, @NotNull String name) {
        return this.compassPlugin.newWaypointBuilder(location)
                .label(name)
                .identifier(this.identifier)
                .build();
    }
}
