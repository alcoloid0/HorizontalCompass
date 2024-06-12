package com.github.alcoloid0.horizontalcompass.settings.holder;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@SuppressWarnings("FieldMayBeFinal")
@ConfigSerializable
public final class WaypointSettingsHolder {
    @Setting("cardinal-color")
    private TextColor cardinalColor = NamedTextColor.AQUA;

    public TextColor getCardinalColor() {
        return cardinalColor;
    }
}
