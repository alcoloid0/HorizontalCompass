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

package com.github.alcoloid0.horizontalcompass.compass.display;

import com.github.alcoloid0.horizontalcompass.api.waypoint.Waypoint;
import com.github.alcoloid0.horizontalcompass.settings.Settings;
import com.github.alcoloid0.horizontalcompass.util.CardinalDirection;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;

public final class SimpleCompassDisplay extends AbstractCompassDisplay {
    @Override
    public void append(int angle, boolean center) {
        this.component.append(Component.text(angle, NamedTextColor.WHITE));
    }

    @Override
    public void append(int angle, @NotNull Waypoint waypoint) {
        this.component.append(format(waypoint.getTextColor(), "%d (%s)", angle, waypoint.getLabel()));

    }

    @Override
    public void append(int angle, @NotNull CardinalDirection direction) {
        this.component.append(format(Settings.waypoints().getCardinalColor(), "%d (%s)", angle, direction.name()));
    }

    @Override
    public int getAngleCount() {
        return 1;
    }
}