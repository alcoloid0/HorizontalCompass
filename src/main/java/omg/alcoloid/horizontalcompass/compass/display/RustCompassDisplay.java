package omg.alcoloid.horizontalcompass.compass.display;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import omg.alcoloid.horizontalcompass.waypoint.CardinalWaypoint;
import omg.alcoloid.horizontalcompass.waypoint.Waypoint;
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
