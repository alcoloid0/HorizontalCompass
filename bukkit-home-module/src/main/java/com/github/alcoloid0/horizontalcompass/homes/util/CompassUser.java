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

package com.github.alcoloid0.horizontalcompass.homes.util;

import com.github.alcoloid0.horizontalcompass.api.compass.Compass;
import com.github.alcoloid0.horizontalcompass.homes.HorizontalCompassHomes;
import net.ess3.api.IUser;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class CompassUser {
    private final IUser user;

    public CompassUser(@NotNull IUser user) {
        this.user = user;
    }

    public static @NotNull CompassUser wrap(@NotNull IUser user) {
        return new CompassUser(user);
    }

    public static @NotNull CompassUser wrap(@NotNull Player player) {
        return CompassUser.wrap(HorizontalCompassHomes.getEssentials().getUser(player));
    }

    public @NotNull Location getHomeLocation(@NotNull String homeName) {
        try {
            return user.getHome(homeName);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public @NotNull Map<String, Location> getHomes() {
        return user.getHomes().stream().collect(Collectors.toMap(Function.identity(), this::getHomeLocation));
    }

    public @NotNull Optional<Compass> getCompass() {
        return HorizontalCompassHomes.getCompassAPI().getCompassByPlayer(user.getBase());
    }

    public @NotNull Player getPlayer() {
        return user.getBase();
    }
}
