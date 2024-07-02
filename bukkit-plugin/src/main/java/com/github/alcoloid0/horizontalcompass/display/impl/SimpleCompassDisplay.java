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

import com.github.alcoloid0.horizontalcompass.api.waypoint.Waypoint;
import com.github.alcoloid0.horizontalcompass.display.AppendableCompassDisplay;
import com.github.alcoloid0.horizontalcompass.settings.Settings;
import com.github.alcoloid0.horizontalcompass.util.Direction;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class SimpleCompassDisplay extends AppendableCompassDisplay {
    @Override
    protected void append(int angle, boolean center) {
        this.append(Component.text(angle));
    }

    @Override
    protected void append(int angle, @NotNull Waypoint waypoint) {
        this.append(format("%d (%s)", angle, waypoint.getLabel()).color(waypoint.getTextColor()));
    }

    @Override
    protected void append(int angle, @NotNull Direction direction) {
        this.append(format("%d (%s)", angle, direction.name()).color(Settings.waypoints().getCardinalColor()));
    }

    @Override
    protected int angleCount() {
        return 1;
    }
}