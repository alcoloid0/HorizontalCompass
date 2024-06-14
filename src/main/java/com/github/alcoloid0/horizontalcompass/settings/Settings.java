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

import com.github.alcoloid0.horizontalcompass.settings.holder.CompassSettingsHolder;
import com.github.alcoloid0.horizontalcompass.settings.holder.DisplaySettingsHolder;
import com.github.alcoloid0.horizontalcompass.settings.holder.SettingsHolder;
import com.github.alcoloid0.horizontalcompass.settings.holder.WaypointSettingsHolder;
import net.kyori.adventure.serializer.configurate4.ConfigurateComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.loader.ConfigurationLoader;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Settings {
    private static final String FILE_NAME = "settings.yml";

    private static Settings instance;

    private final Path filePath;
    private final ConfigurationLoader<?> loader;

    private SettingsHolder holder;

    private Settings(@NotNull File dataFolder) {
        this.filePath = new File(dataFolder, FILE_NAME).toPath();
        this.loader = this.newLoader();
        this.holder = new SettingsHolder();
    }

    public static @NotNull CompassSettingsHolder compass() {
        return Settings.getInstance().getSettingsHolder().getCompassSettings();
    }

    public static @NotNull DisplaySettingsHolder display() {
        return Settings.getInstance().getSettingsHolder().getDisplaySettings();
    }

    public static @NotNull WaypointSettingsHolder waypoints() {
        return Settings.getInstance().getSettingsHolder().getWaypointSettings();
    }

    public static @NotNull Settings getInstance() {
        if (instance == null) {
            throw new RuntimeException("Instance not yet created. Call initialize() first.");
        }

        return instance;
    }

    public static @NotNull Settings initialize(@NotNull File dataFolder) {
        if (instance != null) {
            throw new RuntimeException("Instance already created. Cannot reinitialize.");
        }

        return (instance = new Settings(dataFolder));
    }

    public void save() throws IOException {
        if (Files.notExists(this.filePath)) {
            Files.createDirectories(this.filePath.getParent());
            Files.createFile(this.filePath);
        }

        this.loader.save(this.loader.load().set(SettingsHolder.class, this.holder));
    }

    public void load() throws IOException {
        if (Files.notExists(this.filePath)) {
            this.save();
        }

        this.holder = this.loader.load().get(SettingsHolder.class);
    }

    public @NotNull SettingsHolder getSettingsHolder() {
        return this.holder;
    }

    private @NotNull ConfigurationLoader<?> newLoader() {
        var kyoriSerializers = ConfigurateComponentSerializer.configurate()
                .serializers();

        var options = YamlConfigurationLoader.builder().defaultOptions()
                .shouldCopyDefaults(true)
                .serializers(builder -> builder.registerAll(kyoriSerializers));

        return YamlConfigurationLoader.builder()
                .nodeStyle(NodeStyle.BLOCK)
                .indent(2)
                .path(this.filePath)
                .defaultOptions(options)
                .build();
    }
}
