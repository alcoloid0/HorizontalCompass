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

import com.github.alcoloid0.horizontalcompass.compass.Compass;
import com.github.alcoloid0.horizontalcompass.compass.display.CompassDisplay;
import com.github.alcoloid0.horizontalcompass.compass.display.DegreesCompassDisplay;
import com.github.alcoloid0.horizontalcompass.compass.display.RustCompassDisplay;
import com.github.alcoloid0.horizontalcompass.compass.display.SimpleCompassDisplay;
import com.github.alcoloid0.horizontalcompass.compass.impl.ActionBarCompass;
import com.github.alcoloid0.horizontalcompass.compass.impl.BossBarCompass;
import com.github.alcoloid0.horizontalcompass.settings.Settings;
import com.github.alcoloid0.horizontalcompass.waypoint.factory.WaypointFactory;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class SettingsCompassFactory implements CompassFactory {
    private final Settings settings;
    private final WaypointFactory waypointFactory;

    public SettingsCompassFactory(@NotNull Settings settings, @NotNull WaypointFactory factory) {
        this.settings = settings;
        this.waypointFactory = factory;
    }

    @Override
    public @NotNull Compass createBossBarCompass(@NotNull Player player) {
        Audience audience = this.settings.getPlugin().getAdventure().player(player);
        BossBarCompass compass = new BossBarCompass(player, audience, this.createCompassDisplay());
        compass.setColor(this.settings.getBossBarColor());
        compass.setProgress(this.settings.getBossBarProgress());
        compass.getWaypoints().addAll(this.waypointFactory.getCardinalWaypointList());
        return compass;
    }

    @Override
    public @NotNull Compass createActionBarCompass(@NotNull Player player) {
        Audience audience = this.settings.getPlugin().getAdventure().player(player);
        Compass compass = new ActionBarCompass(player, audience, this.createCompassDisplay());
        compass.getWaypoints().addAll(this.waypointFactory.getCardinalWaypointList());
        return compass;
    }

    private @NotNull CompassDisplay createCompassDisplay() {
        TextColor angle = this.settings.getDegreesAngleColor();
        TextColor center = this.settings.getDegreesCenterAngleColor();
        TextColor color = this.settings.getRustColor();

        return switch (this.settings.getCompassDisplay()) {
            case DEGREES -> new DegreesCompassDisplay(angle, center);
            case RUST -> new RustCompassDisplay(color);
            case SIMPLE -> new SimpleCompassDisplay();
        };
    }
}
