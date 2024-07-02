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

package com.github.alcoloid0.horizontalcompass.homes;

import com.earth2me.essentials.Essentials;
import com.github.alcoloid0.horizontalcompass.api.HorizontalCompassAPI;
import com.github.alcoloid0.horizontalcompass.homes.listener.HomeListener;
import com.github.alcoloid0.horizontalcompass.homes.listener.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class HorizontalCompassHomes extends JavaPlugin {
    private static Essentials essentials;
    private static HorizontalCompassAPI compassAPI;

    public static Essentials getEssentials() {
        return essentials;
    }

    public static HorizontalCompassAPI getCompassAPI() {
        return compassAPI;
    }

    @Override
    public void onEnable() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        compassAPI = Bukkit.getServicesManager().load(HorizontalCompassAPI.class);

        if (compassAPI == null) {
            this.getLogger().severe("Disabled because the HorizontalCompassAPI service could not be loaded");
            pluginManager.disablePlugin(this);
            return;
        }

        essentials = (Essentials) pluginManager.getPlugin("Essentials");

        if (essentials == null) {
            this.getLogger().severe("Disabled because no Essentials dependency found");
            pluginManager.disablePlugin(this);
            return;
        }

        pluginManager.registerEvents(new HomeListener(), this);
        pluginManager.registerEvents(new PlayerJoinListener(), this);
    }
}