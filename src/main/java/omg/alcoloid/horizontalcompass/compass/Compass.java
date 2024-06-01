package omg.alcoloid.horizontalcompass.compass;

import omg.alcoloid.horizontalcompass.compass.display.CompassDisplay;
import omg.alcoloid.horizontalcompass.waypoint.Waypoint;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class Compass {
    protected final Player player;
    private final List<Waypoint> waypoints;
    protected CompassDisplay display;

    protected Compass(@NotNull Player player, @NotNull CompassDisplay display) {
        this.player = player;
        this.display = display;
        this.waypoints = new ArrayList<>();
    }

    public void update() {
        int lookAngle = this.getPlayerLookAngle();

        this.display.flush();

        for (int angle : this.getPlayerLookAngleOffsets()) {
            Optional<Waypoint> waypoint = this.getWaypointByAngle(angle);

            if (waypoint.isEmpty()) {
                this.display.append(angle, angle == lookAngle);
            } else {
                this.display.append(angle, waypoint.get());
            }
        }
    }

    private int @NotNull [] getPlayerLookAngleOffsets() {
        int lookAngle = this.getPlayerLookAngle();

        int[] offsets = new int[this.display.getViewAngleCount()];

        // Заполняем массив смещений относительно центрального угла
        for (int i = 0; i < offsets.length; i++) {
            offsets[i] = Math.floorMod(lookAngle - (offsets.length / 2) + i, 360);
        }

        return offsets;
    }

    protected int getPlayerLookAngle() {
        return Math.round(this.player.getEyeLocation().getYaw()) + 180;
    }

    private @NotNull Optional<Waypoint> getWaypointByAngle(int angle) {
        Location location = this.player.getLocation();

        List<Waypoint> reversedWaypoints = new ArrayList<>(this.waypoints);

        Collections.reverse(reversedWaypoints);

        return reversedWaypoints.stream()
                .filter(waypoint -> waypoint.getRotationAngle(location) == angle)
                .findFirst();
    }

    public @NotNull Player getPlayer() {
        return this.player;
    }

    public @NotNull List<Waypoint> getWaypoints() {
        return this.waypoints;
    }

    public abstract void show();

    public abstract void hide();
}