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

package com.github.alcoloid0.horizontalcompass.settings.holder;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
@ConfigSerializable
public final class DisplaySettingsHolder {
    private DegreesSetting degrees = new DegreesSetting();
    private RustSetting rust = new RustSetting();
    private PurPurSetting purpur = new PurPurSetting();

    public DegreesSetting getDegrees() {
        return degrees;
    }

    public RustSetting getRust() {
        return rust;
    }

    public PurPurSetting getPurpur() {
        return purpur;
    }

    @ConfigSerializable
    public static final class DegreesSetting {
        private TextColor color = NamedTextColor.WHITE;
        @Setting("color-center")
        private TextColor colorCenter = NamedTextColor.LIGHT_PURPLE;

        public TextColor getColor() {
            return color;
        }

        public TextColor getColorCenter() {
            return colorCenter;
        }
    }

    @ConfigSerializable
    public static final class RustSetting {
        private TextColor color = NamedTextColor.WHITE;
        @Setting("marker-symbol")
        private char markerSymbol = '▼';
        private char delimiter = '|';

        public TextColor getColor() {
            return color;
        }

        public char getMarkerSymbol() {
            return markerSymbol;
        }

        public char getDelimiter() {
            return delimiter;
        }
    }

    @ConfigSerializable
    public static final class PurPurSetting {
        @Setting("view-length")
        private int viewLength = 50;
        private String string = """
                S  ·  ◈  ·  ◈  ·  ◈  ·  SW  ·  ◈  ·  ◈  ·  ◈  ·  \
                W  ·  ◈  ·  ◈  ·  ◈  ·  NW  ·  ◈  ·  ◈  ·  ◈  ·  \
                N  ·  ◈  ·  ◈  ·  ◈  ·  NE  ·  ◈  ·  ◈  ·  ◈  ·  \
                E  ·  ◈  ·  ◈  ·  ◈  ·  SE  ·  ◈  ·  ◈  ·  ◈  · \s""";

        public int getViewLength() {
            return viewLength;
        }

        public String getString() {
            return string;
        }
    }
}
