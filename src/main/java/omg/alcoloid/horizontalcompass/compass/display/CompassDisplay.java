package omg.alcoloid.horizontalcompass.compass.display;

import net.kyori.adventure.text.Component;
import omg.alcoloid.horizontalcompass.waypoint.Waypoint;
import org.jetbrains.annotations.NotNull;

public interface CompassDisplay {
    void flush();

    int getViewAngleCount();

    void append(int angle, boolean center);

    void append(int angle, @NotNull Waypoint waypoint);

    @NotNull
    Component getComponent();
}
