package com.github.alcoloid0.horizontalcompass.homes.listener;

import com.github.alcoloid0.horizontalcompass.api.HorizontalCompassAPI;
import com.github.alcoloid0.horizontalcompass.api.compass.Compass;
import com.github.alcoloid0.horizontalcompass.api.waypoint.Waypoint;
import com.github.alcoloid0.horizontalcompass.homes.HorizontalCompassHomes;
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
        Player player = event.getHomeOwner().getBase();

        Compass compass = compassAPI.getCompassByPlayer(player).orElseThrow();

        switch (event.getCause()) {
            case CREATE -> {
                Waypoint waypoint = compassAPI.newWaypointBuilder(event.getNewLocation())
                        .identifier(HomeWaypointIdentifier.at(player, event.getNewName()))
                        .label(event.getNewName())
                        .build();

                compass.getWaypoints().add(waypoint);
            }
            case DELETE -> {
                compass.getWaypoints().remove(HomeWaypointIdentifier.at(player, event.getOldName()));
            }
            case RENAME -> {
                Waypoint waypoint = compass.getWaypoints()
                        .get(HomeWaypointIdentifier.at(player, event.getOldName()))
                        .get(0);

                waypoint.setLabel(event.getNewName());

                if (waypoint.getIdentifier() instanceof HomeWaypointIdentifier identifier) {
                    identifier.setHomeName(event.getNewName());
                }
            }
            case UPDATE -> {
                Waypoint waypoint = compass.getWaypoints()
                        .get(HomeWaypointIdentifier.at(player, event.getNewName()))
                        .get(0);

                waypoint.updateLocation(event.getNewLocation());
            }
        }

        compass.update();
    }
}
