package com.github.alcoloid0.horizontalcompass.api.waypoint;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record WaypointIdentifier(@NotNull String base, @NotNull String id) {
    public static @NotNull WaypointIdentifier plugin(@NotNull Plugin plugin, @NotNull String id) {
        return at(plugin.getName(), id);
    }

    public static @NotNull WaypointIdentifier uuid(@NotNull UUID uuid) {
        return at("uuid", uuid.toString());
    }

    public static @NotNull WaypointIdentifier at(@NotNull String base, @NotNull String id) {
        return new WaypointIdentifier(base, id);
    }

    public @NotNull String getFullId() {
        return "%s:%s".formatted(this.base, this.id);
    }
}