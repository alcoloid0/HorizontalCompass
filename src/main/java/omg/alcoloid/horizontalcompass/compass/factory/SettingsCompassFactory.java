package omg.alcoloid.horizontalcompass.compass.factory;

import net.kyori.adventure.text.format.TextColor;
import omg.alcoloid.horizontalcompass.compass.Compass;
import omg.alcoloid.horizontalcompass.compass.display.CompassDisplay;
import omg.alcoloid.horizontalcompass.compass.display.DegreesCompassDisplay;
import omg.alcoloid.horizontalcompass.compass.display.RustCompassDisplay;
import omg.alcoloid.horizontalcompass.compass.display.SimpleCompassDisplay;
import omg.alcoloid.horizontalcompass.compass.impl.ActionBarCompass;
import omg.alcoloid.horizontalcompass.compass.impl.BossBarCompass;
import omg.alcoloid.horizontalcompass.settings.Settings;
import omg.alcoloid.horizontalcompass.waypoint.factory.WaypointFactory;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class SettingsCompassFactory implements CompassFactory {
    private final Settings settings;
    private final WaypointFactory waypointFactory;

    public SettingsCompassFactory(@NotNull Settings settings, @NotNull WaypointFactory factory) {
        this.settings = settings;
        this.waypointFactory = factory;
    }

    @Override
    public @NotNull Compass createBossBarCompass(@NotNull Player player) {
        BossBarCompass compass = new BossBarCompass(player, this.createCompassDisplay());
        compass.setColor(this.settings.getBossBarColor());
        compass.setProgress(this.settings.getBossBarProgress());
        compass.getWaypoints().addAll(this.waypointFactory.getCardinalWaypointList());
        return compass;
    }

    @Override
    public @NotNull Compass createActionBarCompass(@NotNull Player player) {
        Compass compass = new ActionBarCompass(player, this.createCompassDisplay());
        compass.getWaypoints().addAll(this.waypointFactory.getCardinalWaypointList());
        return compass;
    }

    private @NotNull CompassDisplay createCompassDisplay() {
        TextColor angle = this.settings.getDegreesAngleColor();
        TextColor center = this.settings.getDegreesCenterAngleColor();
        TextColor color = this.settings.getRustColor();

        return switch (this.settings.getCompassDisplay()) {
            case DEGREES -> new DegreesCompassDisplay(angle, center);
            case RUST -> new RustCompassDisplay(color);
            case SIMPLE -> new SimpleCompassDisplay();
        };
    }
}
