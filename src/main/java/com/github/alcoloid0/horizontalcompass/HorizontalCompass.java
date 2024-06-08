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

package com.github.alcoloid0.horizontalcompass;

import com.github.alcoloid0.horizontalcompass.compass.Compass;
import com.github.alcoloid0.horizontalcompass.compass.factory.CompassFactory;
import com.github.alcoloid0.horizontalcompass.compass.factory.SettingsCompassFactory;
import com.github.alcoloid0.horizontalcompass.hook.HookManager;
import com.github.alcoloid0.horizontalcompass.listener.HomeListener;
import com.github.alcoloid0.horizontalcompass.listener.LookListener;
import com.github.alcoloid0.horizontalcompass.listener.PlayerListener;
import com.github.alcoloid0.horizontalcompass.settings.Settings;
import com.github.alcoloid0.horizontalcompass.waypoint.factory.SettingsWaypointFactory;
import com.github.alcoloid0.horizontalcompass.waypoint.factory.WaypointFactory;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class HorizontalCompass extends JavaPlugin {
    private static HorizontalCompass instance;
    private final Map<Player, Compass> playerCompassMap = new HashMap<>();
    private Settings settings;
    private CompassFactory compassFactory;
    private WaypointFactory waypointFactory;
    private HookManager hookManager;
    private BukkitAudiences adventure;

    public static HorizontalCompass getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        this.adventure = BukkitAudiences.create(this);

        this.settings = new Settings(this).load();

        this.waypointFactory = new SettingsWaypointFactory(this.settings);
        this.compassFactory = new SettingsCompassFactory(this.settings, this.waypointFactory);

        this.hookManager = new HookManager(this);

        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerListener(this), this);

        if (this.hookManager.essentials().isPresent()) {
            pluginManager.registerEvents(new HomeListener(this), this);
        }

        this.hookManager.protocolLib().ifPresent(protocolLib -> {
            protocolLib.addPacketListener(new LookListener(this));
        });
    }

    public BukkitAudiences getAdventure() {
        return this.adventure;
    }

    public Map<Player, Compass> getPlayerCompassMap() {
        return this.playerCompassMap;
    }

    public CompassFactory getCompassFactory() {
        return compassFactory;
    }

    public WaypointFactory getWaypointFactory() {
        return waypointFactory;
    }

    public Settings getSettings() {
        return settings;
    }

    public HookManager getHookManager() {
        return hookManager;
    }
}
