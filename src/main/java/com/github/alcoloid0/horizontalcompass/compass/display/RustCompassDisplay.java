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
import org.jetbrains.annotations.NotNull;

public final class RustCompassDisplay extends AbstractCompassDisplay {
    @Override
    public void append(int angle, boolean center) {
        if (angle % 10 == 0) {
            this.component.append(Component.text(angle, Settings.display().getRust().getColor()));
        } else if (angle % 5 == 0) {
            this.component.append(Component.text(Settings.display().getRust().getDelimiter(),
                    Settings.display().getRust().getColor()));
        } else {
            this.component.appendSpace();
        }
    }

    @Override
    public void append(int angle, @NotNull Waypoint waypoint) {
        this.component.append(Component.text(Settings.display().getRust().getMarkerSymbol(),
                waypoint.getTextColor()));
    }

    @Override
    public void append(int angle, @NotNull CardinalDirection direction) {
        this.component.append(Component.text(direction.name(), Settings.display().getRust().getColor()));
    }

    @Override
    public int getAngleCount() {
        return 71;
    }
}
