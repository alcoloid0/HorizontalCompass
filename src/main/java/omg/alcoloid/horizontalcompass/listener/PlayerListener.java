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

import omg.alcoloid.horizontalcompass.HorizontalCompass;
import omg.alcoloid.horizontalcompass.compass.Compass;
import omg.alcoloid.horizontalcompass.compass.factory.CompassFactory;
import omg.alcoloid.horizontalcompass.hook.HookManager;
import omg.alcoloid.horizontalcompass.waypoint.Waypoint;
import omg.alcoloid.horizontalcompass.waypoint.factory.WaypointFactory;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PlayerListener implements Listener {
    private final HorizontalCompass compassPlugin;

    public PlayerListener(@NotNull HorizontalCompass compassPlugin) {
        this.compassPlugin = compassPlugin;
    }

    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();

        CompassFactory factory = HorizontalCompass.getInstance().getCompassFactory();

        Compass compass = switch (this.compassPlugin.getSettings().getCompassType()) {
            case ACTIONBAR -> factory.createActionBarCompass(player);
            case BOSSBAR -> factory.createBossBarCompass(player);
        };

        this.compassPlugin.getPlayerCompassMap().put(event.getPlayer(), compass);

        compass.getWaypoints().addAll(this.getPlayerHomeWaypoints(player));

        compass.update();
        compass.show();
    }

    @EventHandler
    public void onPlayerMove(@NotNull PlayerMoveEvent moveEvent) {
        this.compassPlugin.getPlayerCompassMap().get(moveEvent.getPlayer()).update();
    }

    @EventHandler
    public void onPlayerLeave(@NotNull PlayerQuitEvent quitEvent) {
        Player player = quitEvent.getPlayer();

        this.compassPlugin.getPlayerCompassMap().get(player).hide();
        this.compassPlugin.getPlayerCompassMap().remove(player);
    }

    private @NotNull List<Waypoint> getPlayerHomeWaypoints(@NotNull Player player) {
        List<Waypoint> waypoints = new ArrayList<>();

        if (this.compassPlugin.getSettings().isEssentialsHomeDisabled()) {
            return waypoints;
        }

        HookManager hookManager = this.compassPlugin.getHookManager();
        WaypointFactory factory = this.compassPlugin.getWaypointFactory();

        hookManager.essentials().ifPresent(essentials -> {
            for (String homeName : essentials.getPlayerHomeList(player)) {
                Location homeLocation = essentials.getPlayerHome(player, homeName);
                waypoints.add(factory.createHomeWaypoint(homeLocation, homeName));
            }
        });

        return waypoints;
    }
}
