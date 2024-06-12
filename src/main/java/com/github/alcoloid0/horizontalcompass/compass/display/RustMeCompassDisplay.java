package com.github.alcoloid0.horizontalcompass.compass.display;

import com.github.alcoloid0.horizontalcompass.api.waypoint.Waypoint;
import com.github.alcoloid0.horizontalcompass.settings.Settings;
import com.github.alcoloid0.horizontalcompass.util.CardinalDirection;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class RustMeCompassDisplay extends AbstractCompassDisplay {
    @Override
    public void append(int angle, boolean center) {
        if (angle % 15 == 0) {
            this.component.append(Component.text(angle, Settings.display().getRust().getColor()));
        } else if ((angle % 15) % 3 == 0) {
            this.component.append(Component.text(Settings.display().getRust().getDelimiter(),
                    Settings.display().getRust().getColor()));
        } else {
            this.component.appendSpace();
        }
    }

    @Override
    public void append(int angle, @NotNull Waypoint waypoint) {
        this.component.append(Component.text(Settings.display().getRust().getMarkerSymbol(),
                waypoint.getTextColor()));
    }

    @Override
    public void append(int angle, @NotNull CardinalDirection direction) {
        this.component.append(Component.text(direction.name(), Settings.waypoints().getCardinalColor()));
    }

    @Override
    public int getAngleCount() {
        return 81;
    }
}
