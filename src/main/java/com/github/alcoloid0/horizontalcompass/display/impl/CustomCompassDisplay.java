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

package com.github.alcoloid0.horizontalcompass.display.impl;

import com.github.alcoloid0.horizontalcompass.api.compass.Compass;
import com.github.alcoloid0.horizontalcompass.display.CompassDisplay;
import com.github.alcoloid0.horizontalcompass.settings.Settings;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class CustomCompassDisplay implements CompassDisplay {
    private Component component = Component.empty();

    @Override
    public void update(@NotNull Compass forCompass) {
        float yaw = forCompass.getCompassPlayer().getEyeLocation().getYaw() + 180.0f;

        int repeatCount = (this.viewLength() / this.getString().length()) + 2;

        String repeatTitle = this.getString().repeat(repeatCount);

        int startIndex = (int) (yaw * (repeatTitle.length() / (360.0f * repeatCount)));
        int endIndex = startIndex + this.viewLength();

        this.component = Component.text(repeatTitle.substring(startIndex, endIndex));
    }

    @Override
    public @NotNull Component getComponent() {
        return this.component;
    }

    private @NotNull String getString() {
        return Settings.display().getCustom().getString();
    }

    private int viewLength() {
        return Settings.display().getCustom().getViewLength();
    }
}