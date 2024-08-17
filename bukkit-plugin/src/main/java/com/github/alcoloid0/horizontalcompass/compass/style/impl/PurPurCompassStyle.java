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

package com.github.alcoloid0.horizontalcompass.compass.style.impl;

import com.github.alcoloid0.horizontalcompass.api.compass.Compass;
import com.github.alcoloid0.horizontalcompass.compass.style.CompassStyle;
import com.github.alcoloid0.horizontalcompass.settings.Settings;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public final class PurPurCompassStyle implements CompassStyle {
    private Component component = Component.empty();

    @Override
    public void update(@NotNull Compass forCompass) {
        float yaw = forCompass.getCompassPlayer().getEyeLocation().getYaw();

        int repeatCount = ((this.viewLength() / this.getString().length()) + 1) * 2;

        String string = String.join("", Collections.nCopies(repeatCount, this.getString()));

        int length = string.length();

        int center = (int) (yaw * (length / (360.0f * repeatCount)) + (length / 2.0f));

        int startIndex = center - (this.viewLength() / 2);
        int endIndex = center + (this.viewLength() / 2);

        this.component = Component.text(string.substring(startIndex, endIndex));
    }

    @Override
    public @NotNull Component getComponent() {
        return this.component;
    }

    private @NotNull String getString() {
        return Settings.display().getPurpur().getString();
    }

    private int viewLength() {
        return Settings.display().getPurpur().getViewLength();
    }
}