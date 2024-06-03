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

import com.github.alcoloid0.horizontalcompass.waypoint.CardinalWaypoint;
import com.github.alcoloid0.horizontalcompass.waypoint.Waypoint;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

public final class RustCompassDisplay implements CompassDisplay {
    private static final String DELIMITER = "|";

    private final TextColor color;
    private TextComponent.Builder component;

    public RustCompassDisplay(@NotNull TextColor color) {
        this.color = color;

        this.flush();
    }

    @Override
    public void flush() {
        this.component = Component.text();
    }

    @Override
    public int getViewAngleCount() {
        return 71;
    }

    @Override
    public void append(int angle, boolean center) {
        if (angle % 10 == 0) {
            this.component.append(Component.text(angle, this.color));
        } else if (angle % 5 == 0) {
            this.component.append(Component.text(DELIMITER, this.color));
        }

        this.component.appendSpace();
    }

    @Override
    public void append(int angle, @NotNull Waypoint waypoint) {
        if (waypoint instanceof CardinalWaypoint cardinal) {
            this.component.append(Component.text(cardinal.getDirection().name(), this.color));
        } else {
            this.component.append(Component.text(waypoint.getMarkerSymbol(), waypoint.getTextColor()));
        }
    }

    @Override
    public @NotNull Component getComponent() {
        return this.component.build();
    }
}
