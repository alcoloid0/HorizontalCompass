package omg.alcoloid.horizontalcompass.compass.display;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import omg.alcoloid.horizontalcompass.waypoint.Waypoint;
import org.jetbrains.annotations.NotNull;

public final class SimpleCompassDisplay implements CompassDisplay {
    private TextComponent.Builder component;

    public SimpleCompassDisplay() {
        this.flush();
    }

    @Override
    public void flush() {
        this.component = Component.text();
    }

    @Override
    public int getViewAngleCount() {
        return 1;
    }

    @Override
    public void append(int angle, boolean center) {
        this.component.append(Component.text(angle, NamedTextColor.WHITE));
    }

    @Override
    public void append(int angle, @NotNull Waypoint waypoint) {
        String text = String.format("%d (%c)", angle, waypoint.getMarkerSymbol());
        this.component.append(Component.text(text, waypoint.getTextColor()));
    }

    @Override
    public @NotNull Component getComponent() {
        return this.component.build();
    }
}