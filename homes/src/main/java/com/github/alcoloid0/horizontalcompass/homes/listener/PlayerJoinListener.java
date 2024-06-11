package com.github.alcoloid0.horizontalcompass.homes.listener;

import com.github.alcoloid0.horizontalcompass.api.HorizontalCompassAPI;
import com.github.alcoloid0.horizontalcompass.api.compass.Compass;
import com.github.alcoloid0.horizontalcompass.api.waypoint.Waypoint;
import com.github.alcoloid0.horizontalcompass.homes.HorizontalCompassHomes;
import com.github.alcoloid0.horizontalcompass.homes.util.EssentialsUtil;
import com.github.alcoloid0.horizontalcompass.homes.waypoint.HomeWaypointIdentifier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public final class PlayerJoinListener implements Listener {
    private final HorizontalCompassAPI compassAPI = HorizontalCompassHomes.getCompassAPI();

    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        Compass compass = compassAPI.getCompassByPlayer(event.getPlayer()).orElseThrow();

        EssentialsUtil.getHomes(event.getPlayer()).forEach((name, location) -> {
            Waypoint waypoint = compassAPI.newWaypointBuilder(location)
                    .identifier(HomeWaypointIdentifier.at(event.getPlayer(), name))
                    .label(name)
                    .build();

            compass.getWaypoints().add(waypoint);
        });

        compass.update();
    }
}
