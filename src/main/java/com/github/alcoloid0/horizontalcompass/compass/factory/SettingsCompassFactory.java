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

package com.github.alcoloid0.horizontalcompass.compass.factory;

import com.github.alcoloid0.horizontalcompass.HorizontalCompass;
import com.github.alcoloid0.horizontalcompass.api.compass.Compass;
import com.github.alcoloid0.horizontalcompass.compass.display.CompassDisplay;
import com.github.alcoloid0.horizontalcompass.compass.display.DegreesCompassDisplay;
import com.github.alcoloid0.horizontalcompass.compass.display.RustCompassDisplay;
import com.github.alcoloid0.horizontalcompass.compass.display.SimpleCompassDisplay;
import com.github.alcoloid0.horizontalcompass.compass.impl.ActionBarCompass;
import com.github.alcoloid0.horizontalcompass.compass.impl.BossBarCompass;
import com.github.alcoloid0.horizontalcompass.settings.Settings;
import com.github.alcoloid0.horizontalcompass.settings.type.CompassDisplayType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public final class SettingsCompassFactory implements CompassFactory {
    private static final Map<CompassDisplayType, CompassDisplay> DISPLAY_MAP = Map.of(
            CompassDisplayType.DEGREES, new DegreesCompassDisplay(),
            CompassDisplayType.SIMPLE, new SimpleCompassDisplay(),
            CompassDisplayType.RUST, new RustCompassDisplay()
    );

    private final HorizontalCompass compassPlugin;

    public SettingsCompassFactory(@NotNull HorizontalCompass compassPlugin) {
        this.compassPlugin = compassPlugin;
    }

    @Override
    public @NotNull Compass createCompass(@NotNull Player forPlayer) {
        CompassDisplay display = DISPLAY_MAP.get(Settings.compass().getDisplay());

        return switch (Settings.compass().getType()) {
            case ACTIONBAR -> new ActionBarCompass(this.compassPlugin, forPlayer, display);
            case BOSSBAR -> new BossBarCompass(this.compassPlugin, forPlayer, display);
        };
    }
}
