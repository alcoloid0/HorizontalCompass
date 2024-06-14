package com.github.alcoloid0.horizontalcompass.display.impl;

import com.github.alcoloid0.horizontalcompass.api.waypoint.Waypoint;
import com.github.alcoloid0.horizontalcompass.display.AppendableCompassDisplay;
import com.github.alcoloid0.horizontalcompass.settings.Settings;
import com.github.alcoloid0.horizontalcompass.util.CardinalDirection;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

public final class RustMeCompassDisplay extends AppendableCompassDisplay {
    @Override
    protected void append(int angle, boolean center) {
        TextColor color = Settings.display().getRust().getColor();

        if (angle % 15 == 0) {
            this.append(Component.text(angle, color));
        } else if ((angle % 15) % 3 == 0) {
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
    protected void append(int angle, @NotNull CardinalDirection direction) {
        this.append(Component.text(direction.name(), Settings.waypoints().getCardinalColor()));
    }

    @Override
    protected int angleCount() {
        return 81;
    }
}
