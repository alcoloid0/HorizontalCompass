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

package com.github.alcoloid0.horizontalcompass.display;

import com.github.alcoloid0.horizontalcompass.display.impl.*;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public enum CompassDisplayType {
    DEGREES(DegreesCompassDisplay::new),
    SIMPLE(SimpleCompassDisplay::new),
    RUST(RustCompassDisplay::new),
    RUSTME(RustMeCompassDisplay::new),
    PURPUR(PurPurCompassDisplay::new);

    private final Supplier<CompassDisplay> supplier;

    CompassDisplayType(@NotNull Supplier<CompassDisplay> supplier) {
        this.supplier = supplier;
    }

    public CompassDisplay newInstance() {
        return this.supplier.get();
    }
}
