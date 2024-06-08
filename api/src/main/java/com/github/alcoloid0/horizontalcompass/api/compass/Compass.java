package com.github.alcoloid0.horizontalcompass.api.compass;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public interface Compass {
    @NotNull
    CompassWaypoints getWaypoints();

    @NotNull
    Player getCompassPlayer();

    void hide();

    void show();

    @ApiStatus.Internal
    void update();
}
