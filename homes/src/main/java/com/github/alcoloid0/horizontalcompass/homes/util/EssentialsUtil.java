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

import com.github.alcoloid0.horizontalcompass.homes.HorizontalCompassHomes;
import net.ess3.api.IUser;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public final class EssentialsUtil {
    private EssentialsUtil() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static @NotNull Map<String, Location> getHomes(@Nullable IUser user) {
        Map<String, Location> homes = new HashMap<>();

        if (user != null) {
            try {
                for (String homeName : user.getHomes()) {
                    homes.put(homeName, user.getHome(homeName));
                }
            } catch (Exception ignored) {
            }
        }

        return homes;
    }

    public static @NotNull Map<String, Location> getHomes(@NotNull Player player) {
        return EssentialsUtil.getHomes(HorizontalCompassHomes.getEssentials().getUser(player));
    }
}
