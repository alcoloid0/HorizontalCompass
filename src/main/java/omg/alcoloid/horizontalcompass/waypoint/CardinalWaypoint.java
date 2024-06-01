package omg.alcoloid.horizontalcompass.waypoint;

import net.kyori.adventure.text.format.TextColor;
import omg.alcoloid.horizontalcompass.util.CardinalDirection;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public final class CardinalWaypoint implements Waypoint {
    private final CardinalDirection direction;
    private final TextColor color;

    public CardinalWaypoint(@NotNull CardinalDirection direction, @NotNull TextColor color) {
        this.direction = direction;
        this.color = color;
    }

    @Override
    public int getRotationAngle(@NotNull Location location) {
        return this.direction.getRotationAngle();
    }

    @Override
    public @NotNull TextColor getTextColor() {
        return this.color;
    }

    @Override
    public char getMarkerSymbol() {
        return 0;
    }

    public CardinalDirection getDirection() {
        return direction;
    }
}