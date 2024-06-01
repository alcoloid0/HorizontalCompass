package omg.alcoloid.horizontalcompass.compass.display;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import omg.alcoloid.horizontalcompass.waypoint.Waypoint;
import org.jetbrains.annotations.NotNull;

public final class DegreesCompassDisplay implements CompassDisplay {
    private final TextColor angleColor;
    private final TextColor centerColor;
    private TextComponent.Builder component;

    public DegreesCompassDisplay(@NotNull TextColor angleColor,
                                 @NotNull TextColor centerColor) {

        this.angleColor = angleColor;
        this.centerColor = centerColor;

        this.flush();
    }

    @Override
    public void flush() {
        this.component = Component.text();
    }

    @Override
    public int getViewAngleCount() {
        return 15;
    }

    @Override
    public void append(int angle, boolean center) {
        if (center) {
            this.component.append(this.angleToComponent(angle, this.centerColor));
        } else {
            this.component.append(this.angleToComponent(angle, this.angleColor));
        }

        this.component.appendSpace();
    }

    @Override
    public void append(int angle, @NotNull Waypoint waypoint) {
        this.component.append(this.angleToComponent(angle, waypoint.getTextColor()));
        this.component.appendSpace();
    }

    @Override
    public @NotNull Component getComponent() {
        return this.component.build();
    }

    private @NotNull Component angleToComponent(int angle, TextColor color) {
        return Component.text(String.format("%03d", angle), color);
    }
}