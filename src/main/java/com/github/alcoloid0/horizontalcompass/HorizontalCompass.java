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

import com.github.alcoloid0.horizontalcompass.api.HorizontalCompassAPI;
import com.github.alcoloid0.horizontalcompass.api.compass.Compass;
import com.github.alcoloid0.horizontalcompass.api.waypoint.WaypointBuilder;
import com.github.alcoloid0.horizontalcompass.compass.factory.CompassFactory;
import com.github.alcoloid0.horizontalcompass.compass.factory.SettingsCompassFactory;
import com.github.alcoloid0.horizontalcompass.hook.HookManager;
import com.github.alcoloid0.horizontalcompass.listener.HomeListener;
import com.github.alcoloid0.horizontalcompass.listener.LookPacketListener;
import com.github.alcoloid0.horizontalcompass.listener.PlayerListener;
import com.github.alcoloid0.horizontalcompass.settings.Settings;
import com.github.alcoloid0.horizontalcompass.waypoint.WaypointBuilderImpl;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class HorizontalCompass extends JavaPlugin implements HorizontalCompassAPI {
    private final Map<Player, Compass> playerCompassMap = new HashMap<>();

    private Settings settings;
    private CompassFactory compassFactory;
    private HookManager hookManager;
    private BukkitAudiences adventure;

    @Override
    public void onEnable() {
        this.adventure = BukkitAudiences.create(this);
        this.settings = new Settings(this).load();
        this.compassFactory = new SettingsCompassFactory(this, this.settings);
        this.hookManager = new HookManager(this);

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerListener(this), this);

        if (this.hookManager.essentials().isPresent()) {
            pluginManager.registerEvents(new HomeListener(this), this);
        }

        this.hookManager.protocolLib().ifPresent(protocolLib -> {
            protocolLib.addPacketListener(new LookPacketListener(this));
        });
    }

    @Override
    public @NotNull @Unmodifiable List<Compass> getCompassList() {
        return List.copyOf(this.playerCompassMap.values());
    }

    @Override
    public @NotNull Optional<Compass> getCompassByPlayer(@NotNull OfflinePlayer player) {
        if (!player.isOnline()) {
            return Optional.empty();
        }

        return this.getCompassList().stream()
                .filter(compass -> compass.getCompassPlayer().equals(player.getPlayer()))
                .findFirst();
    }

    @Override
    public @NotNull WaypointBuilder newWaypointBuilder(@NotNull Location location) {
        return new WaypointBuilderImpl(location);
    }

    public Map<Player, Compass> getPlayerCompassMap() {
        return playerCompassMap;
    }

    public BukkitAudiences getAdventure() {
        return this.adventure;
    }

    public CompassFactory getCompassFactory() {
        return compassFactory;
    }

    public Settings getSettings() {
        return settings;
    }

    public HookManager getHookManager() {
        return hookManager;
    }
}
