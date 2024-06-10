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
    private final Settings settings;

    public RustCompassDisplay(@NotNull Settings settings) {
        this.settings = settings;
    }

    @Override
    public void append(int angle, boolean center) {
        if (angle % 10 == 0) {
            this.component.append(Component.text(angle, this.settings.getRustColor()));
        } else if (angle % 5 == 0) {
            this.component.append(Component.text(this.settings.getRustDelimiter(), this.settings.getRustColor()));
        } else {
            this.component.appendSpace();
        }
    }

    @Override
    public void append(int angle, @NotNull Waypoint waypoint) {
        this.component.append(Component.text(this.settings.getRustMarker(), waypoint.getTextColor()));
    }

    @Override
    public void append(int angle, @NotNull CardinalDirection direction) {
        this.component.append(Component.text(direction.name(), this.settings.getRustColor()));
    }

    @Override
    public int getAngleCount() {
        return 71;
    }
}
