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
import com.github.alcoloid0.horizontalcompass.compass.AbstractCompass;
import com.github.alcoloid0.horizontalcompass.compass.display.CompassDisplay;
import com.github.alcoloid0.horizontalcompass.compass.display.DegreesCompassDisplay;
import com.github.alcoloid0.horizontalcompass.compass.display.RustCompassDisplay;
import com.github.alcoloid0.horizontalcompass.compass.display.SimpleCompassDisplay;
import com.github.alcoloid0.horizontalcompass.compass.impl.ActionBarCompass;
import com.github.alcoloid0.horizontalcompass.compass.impl.BossBarCompass;
import com.github.alcoloid0.horizontalcompass.settings.Settings;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class SettingsCompassFactory implements CompassFactory {
    private final HorizontalCompass compassPlugin;
    private final Settings settings;

    public SettingsCompassFactory(@NotNull HorizontalCompass compassPlugin,
                                  @NotNull Settings settings) {

        this.compassPlugin = compassPlugin;
        this.settings = settings;
    }

    @Override
    public @NotNull AbstractCompass createBossBarCompass(@NotNull Player player) {
        CompassDisplay display = this.createCompassDisplay();
        BossBarCompass compass = new BossBarCompass(this.compassPlugin, player, display);
        compass.setColor(this.settings.getBossBarColor());
        compass.setProgress(this.settings.getBossBarProgress());
        return compass;
    }

    @Override
    public @NotNull AbstractCompass createActionBarCompass(@NotNull Player player) {
        CompassDisplay display = this.createCompassDisplay();

        return new ActionBarCompass(this.compassPlugin, player, display);
    }

    private @NotNull CompassDisplay createCompassDisplay() {
        return switch (this.settings.getCompassDisplay()) {
            case DEGREES -> new DegreesCompassDisplay(this.settings);
            case RUST -> new RustCompassDisplay(this.settings);
            case SIMPLE -> new SimpleCompassDisplay(this.settings);
        };
    }
}
