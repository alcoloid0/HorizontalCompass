package com.github.alcoloid0.horizontalcompass.homes.waypoint;

import com.github.alcoloid0.horizontalcompass.api.waypoint.WaypointIdentifier;
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
