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

package com.github.alcoloid0.horizontalcompass.hook;

import com.earth2me.essentials.IEssentials;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class EssentialsHook implements Hook {
    private final IEssentials essentials;

    public EssentialsHook(@NotNull Plugin essentials) {
        this.essentials = (IEssentials) essentials;
    }

    public List<String> getPlayerHomeList(@NotNull Player player) {
        return this.essentials.getUser(player).getHomes();
    }

    public Location getPlayerHome(@NotNull Player player, @NotNull String homeName) {
        return this.essentials.getUser(player).getHome(homeName);
    }

    @Override
    public @NotNull Plugin getPlugin() {
        return this.essentials;
    }
}
