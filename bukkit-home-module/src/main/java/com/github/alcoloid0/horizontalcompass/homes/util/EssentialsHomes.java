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

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EssentialsHomes {
    private static final Essentials ESSENTIALS;

    static {
        ESSENTIALS = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
    }

    private EssentialsHomes() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static @NotNull Location getHomeLocation(@NotNull Player player, @NotNull String homeName) {
        try {
            return Objects.requireNonNull(ESSENTIALS).getUser(player).getHome(homeName);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public static @NotNull Map<String, Location> getHomes(@NotNull Player player) {
        User user = Objects.requireNonNull(ESSENTIALS).getUser(player);
        Function<String, Location> function = homeName -> getHomeLocation(player, homeName);

        return user.getHomes().stream().collect(Collectors.toMap(Function.identity(), function));
    }
}
