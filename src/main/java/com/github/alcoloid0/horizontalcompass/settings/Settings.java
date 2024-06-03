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

package com.github.alcoloid0.horizontalcompass.settings;

import com.github.alcoloid0.horizontalcompass.HorizontalCompass;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Optional;

public final class Settings {
    private static final String DEFAULT_HOME_MARKER = "▼";
    private static final String SETTINGS_FILE_NAME = "settings.yml";

    private final HorizontalCompass plugin;
    private final File file;
    private FileConfiguration config;

    public Settings(@NotNull HorizontalCompass plugin) {
        this.plugin = plugin;
        this.file = new File(this.plugin.getDataFolder(), SETTINGS_FILE_NAME);
    }

    public @NotNull Settings load() {
        if (!this.file.exists()) {
            this.plugin.saveResource(SETTINGS_FILE_NAME, false);
        }

        this.config = YamlConfiguration.loadConfiguration(this.file);
        return this;
    }

    public @NotNull CompassTypeSetting getCompassType() {
        return this.getConfigEnum("compass-settings.type", CompassTypeSetting.ACTIONBAR);
    }

    public @NotNull CompassDisplaySetting getCompassDisplay() {
        return this.getConfigEnum("compass-settings.display", CompassDisplaySetting.SIMPLE);
    }

    public @NotNull BossBar.Color getBossBarColor() {
        return this.getConfigEnum("compass-settings.bossbar.color", BossBar.Color.WHITE);
    }

    public boolean getBossBarProgress() {
        return this.config.getBoolean("compass-settings.bossbar.progress", false);
    }

    public @NotNull TextColor getDegreesAngleColor() {
        return this.getConfigColor("display-settings.degrees.angle-color", NamedTextColor.WHITE);
    }

    public @NotNull TextColor getDegreesCenterAngleColor() {
        return this.getConfigColor("display-settings.angle-color-center", NamedTextColor.LIGHT_PURPLE);
    }

    public @NotNull TextColor getRustColor() {
        return this.getConfigColor("display-settings.rust.color", NamedTextColor.WHITE);
    }

    public @NotNull TextColor getCardinalColor() {
        return this.getConfigColor("waypoint-settings.cardinal-color", NamedTextColor.AQUA);
    }

    public char getLocationMarker() {
        return this.getHomeMarker();
    }

    public char getHomeMarker() {
        return this.config.getString("waypoint-settings.home-marker", DEFAULT_HOME_MARKER).charAt(0);
    }

    public boolean isEssentialsHomeDisabled() {
        return !this.config.getBoolean("essentials-home", true);
    }

    public HorizontalCompass getPlugin() {
        return this.plugin;
    }

    private @NotNull TextColor getConfigColor(String path, TextColor defaultColor) {
        if (!this.config.contains(path)) {
            return defaultColor;
        }

        TextColor color = TextColor.fromHexString(this.config.getString(path, ""));

        return Optional.ofNullable(color).orElse(defaultColor);
    }

    @SuppressWarnings("unchecked")
    private @NotNull <T extends Enum<T>> T getConfigEnum(String path, T defaultEnumValue) {
        if (!this.config.contains(path)) {
            return defaultEnumValue;
        }

        String stringEnumValue = this.config.getString(path, "").trim().toUpperCase();

        try {
            return (T) Enum.valueOf(defaultEnumValue.getClass(), stringEnumValue);
        } catch (Exception exception) {
            return defaultEnumValue;
        }
    }
}
