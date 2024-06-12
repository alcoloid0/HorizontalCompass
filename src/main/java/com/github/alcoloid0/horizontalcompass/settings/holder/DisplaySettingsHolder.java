package com.github.alcoloid0.horizontalcompass.settings.holder;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@SuppressWarnings("FieldMayBeFinal")
@ConfigSerializable
public final class DisplaySettingsHolder {
    private DegreesSetting degrees = new DegreesSetting();
    private RustSetting rust = new RustSetting();

    public DegreesSetting getDegrees() {
        return degrees;
    }

    public RustSetting getRust() {
        return rust;
    }

    @SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
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

    @SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
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
}
