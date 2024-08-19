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

package com.github.alcoloid0.horizontalcompass.compass;

import com.github.alcoloid0.horizontalcompass.api.compass.Compass;
import com.github.alcoloid0.horizontalcompass.compass.factory.CompassFactory;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public final class CompassRegistry {
    private final Map<Player, Compass> playerCompassMap = new ConcurrentHashMap<>();
    private final CompassFactory compassFactory;

    public CompassRegistry(@NotNull CompassFactory compassFactory) {
        this.compassFactory = compassFactory;
    }

    public @NotNull Compass create(@NotNull Player player) {
        return this.playerCompassMap.computeIfAbsent(player, compassFactory::createCompass);
    }

    public @Nullable Compass get(@NotNull Player player) {
        return this.playerCompassMap.get(player);
    }

    public void remove(@NotNull Compass compass) {
        this.playerCompassMap.values().remove(compass);
    }

    public @NotNull @Unmodifiable List<Compass> getCompassList() {
        return Collections.unmodifiableList(new ArrayList<>(this.playerCompassMap.values()));
    }
}
