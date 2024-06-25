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
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

public final class RustCompassDisplay extends AppendableCompassDisplay {
    @Override
    protected void append(int angle, boolean center) {
        TextColor color = Settings.display().getRust().getColor();

        if (angle % 10 == 0) {
            this.append(Component.text(angle, color));
        } else if (angle % 5 == 0) {
            this.append(Component.text(Settings.display().getRust().getDelimiter(), color));
        } else {
            this.appendSpace();
        }
    }

    @Override
    protected void append(int angle, @NotNull Waypoint waypoint) {
        this.append(Component.text(Settings.display().getRust().getMarkerSymbol(), waypoint.getTextColor()));
    }

    @Override
    protected void append(int angle, @NotNull Direction direction) {
        this.append(Component.text(direction.name(), Settings.display().getRust().getColor()));
    }

    @Override
    protected int angleCount() {
        return 71;
    }
}
