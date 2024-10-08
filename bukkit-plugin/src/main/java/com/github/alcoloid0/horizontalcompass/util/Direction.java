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

package com.github.alcoloid0.horizontalcompass.util;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Direction {
    N(0), NE(45), E(90), SE(135), S(180), SW(225), W(270), NW(315);

    private final int azimuth;

    Direction(int azimuth) {
        this.azimuth = azimuth;
    }

    public int azimuth() {
        return this.azimuth;
    }

    public static @Nullable Direction byAzimuth(int azimuth) {
        Predicate<Direction> filter = direction -> direction.azimuth() == azimuth;

        return Arrays.stream(Direction.values()).filter(filter).findFirst().orElse(null);
    }
}
