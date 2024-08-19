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
import com.github.alcoloid0.horizontalcompass.compass.style.CompassStyle;
import com.github.alcoloid0.horizontalcompass.compass.style.impl.DegreesCompassStyle;
import com.github.alcoloid0.horizontalcompass.compass.style.impl.RustCompassStyle;
import com.github.alcoloid0.horizontalcompass.compass.style.impl.SimpleCompassStyle;
import com.github.alcoloid0.horizontalcompass.settings.Settings;
import com.github.alcoloid0.horizontalcompass.settings.setting.CompassStyleTypeSetting;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.function.Supplier;

public final class SettingsCompassFactory implements CompassFactory {
    private static final EnumMap<CompassStyleTypeSetting, Supplier<CompassStyle>> STYLE_FACTORY;

    static {
        STYLE_FACTORY = new EnumMap<>(CompassStyleTypeSetting.class);

        STYLE_FACTORY.put(CompassStyleTypeSetting.DEGREES, DegreesCompassStyle::new);
        STYLE_FACTORY.put(CompassStyleTypeSetting.SIMPLE, SimpleCompassStyle::new);
        STYLE_FACTORY.put(CompassStyleTypeSetting.RUST, RustCompassStyle::new);
    }

    private final HorizontalCompass compassPlugin;

    public SettingsCompassFactory(@NotNull HorizontalCompass compassPlugin) {
        this.compassPlugin = compassPlugin;
    }

    @Override
    public @NotNull Compass createCompass(@NotNull Player forPlayer) {
        CompassStyle compassStyle = STYLE_FACTORY.get(Settings.compass().getStyle()).get();

        switch (Settings.compass().getType()) {
            case ACTIONBAR:
                return new ActionBarCompass(this.compassPlugin, forPlayer, compassStyle);
            default:
            case BOSSBAR:
                return new BossBarCompass(this.compassPlugin, forPlayer, compassStyle);
        }
    }
}
