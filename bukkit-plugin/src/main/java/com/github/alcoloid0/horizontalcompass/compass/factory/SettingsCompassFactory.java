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
import com.github.alcoloid0.horizontalcompass.compass.impl.ActionBarCompass;
import com.github.alcoloid0.horizontalcompass.compass.impl.BossBarCompass;
import com.github.alcoloid0.horizontalcompass.display.CompassDisplay;
import com.github.alcoloid0.horizontalcompass.display.impl.*;
import com.github.alcoloid0.horizontalcompass.settings.Settings;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class SettingsCompassFactory implements CompassFactory {
    private final HorizontalCompass compassPlugin;

    public SettingsCompassFactory(@NotNull HorizontalCompass compassPlugin) {
        this.compassPlugin = compassPlugin;
    }

    @Override
    public @NotNull Compass createCompass(@NotNull Player forPlayer) {
        CompassDisplay display = switch (Settings.compass().getDisplay()) {
            case DEGREES -> new DegreesCompassDisplay();
            case SIMPLE -> new SimpleCompassDisplay();
            case RUST -> new RustCompassDisplay();
            case RUSTME -> new RustMeCompassDisplay();
            case PURPUR -> new PurPurCompassDisplay();
        };

        return switch (Settings.compass().getType()) {
            case ACTIONBAR -> new ActionBarCompass(this.compassPlugin, forPlayer, display);
            case BOSSBAR -> new BossBarCompass(this.compassPlugin, forPlayer, display);
        };
    }
}
