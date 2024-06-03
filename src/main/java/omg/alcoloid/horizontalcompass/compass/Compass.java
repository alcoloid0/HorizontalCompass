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

package omg.alcoloid.horizontalcompass.compass;

import net.kyori.adventure.audience.Audience;
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
    protected final Audience audience;
    private final List<Waypoint> waypoints;
    protected CompassDisplay display;

    protected Compass(@NotNull Player player,
                      @NotNull Audience audience,
                      @NotNull CompassDisplay display) {

        this.player = player;
        this.audience = audience;
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