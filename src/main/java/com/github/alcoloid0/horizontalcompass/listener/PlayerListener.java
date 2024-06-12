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
import com.github.alcoloid0.horizontalcompass.compass.factory.CompassFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public final class PlayerListener implements Listener {
    private final HorizontalCompass compassPlugin;

    public PlayerListener(@NotNull HorizontalCompass compassPlugin) {
        this.compassPlugin = compassPlugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Compass compass = compassPlugin.getCompassFactory().createCompass(player);

        this.compassPlugin.getPlayerCompassMap().put(event.getPlayer(), compass);

        compass.update();
        compass.show();
    }

    @EventHandler
    public void onPlayerMove(@NotNull PlayerMoveEvent moveEvent) {
        this.compassPlugin.getPlayerCompassMap().get(moveEvent.getPlayer()).update();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerLeave(@NotNull PlayerQuitEvent quitEvent) {
        Player player = quitEvent.getPlayer();

        this.compassPlugin.getPlayerCompassMap().get(player).hide();
        this.compassPlugin.getPlayerCompassMap().remove(player);
    }
}
