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

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractCompassDisplay implements CompassDisplay {
    protected TextComponent.Builder component;

    public AbstractCompassDisplay() {
        this.flush();
    }

    @Override
    public void flush() {
        this.component = Component.text();
    }

    @Override
    public @NotNull Component getComponent() {
        return this.component.build();
    }

    protected @NotNull Component format(@NotNull TextColor color,
                                        @NotNull String format,
                                        @NotNull Object... args) {

        return Component.text(String.format(format, args), color);
    }
}
