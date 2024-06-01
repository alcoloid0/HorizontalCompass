package omg.alcoloid.horizontalcompass.compass.factory;

import omg.alcoloid.horizontalcompass.compass.Compass;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface CompassFactory {
    @NotNull
    Compass createBossBarCompass(@NotNull Player player);

    @NotNull
    Compass createActionBarCompass(@NotNull Player player);
}