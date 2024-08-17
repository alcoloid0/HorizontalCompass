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

import com.github.alcoloid0.horizontalcompass.api.waypoint.Waypoint;
import com.github.alcoloid0.horizontalcompass.compass.style.AppendableCompassStyle;
import com.github.alcoloid0.horizontalcompass.settings.Settings;
import com.github.alcoloid0.horizontalcompass.util.Direction;
import org.jetbrains.annotations.NotNull;

public final class DegreesCompassStyle extends AppendableCompassStyle {
    @Override
    protected void append(int angle, boolean center) {
        if (center) {
            this.append(format("%03d", angle).color(Settings.display().getDegrees().getColorCenter()));
        } else {
            this.append(format("%03d", angle).color(Settings.display().getDegrees().getColor()));
        }

        this.appendSpace();
    }

    @Override
    protected void append(int angle, @NotNull Waypoint waypoint) {
        this.append(format("%03d", angle).color(waypoint.getTextColor()));
        this.appendSpace();
    }

    @Override
    protected void append(int angle, @NotNull Direction direction) {
        this.append(format("%03d", angle).color(Settings.waypoints().getCardinalColor()));
        this.appendSpace();
    }

    @Override
    protected int angleCount() {
        return 15;
    }
}