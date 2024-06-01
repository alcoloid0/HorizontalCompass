package omg.alcoloid.horizontalcompass.compass.impl;

import omg.alcoloid.horizontalcompass.compass.Compass;
import omg.alcoloid.horizontalcompass.compass.display.CompassDisplay;
import omg.alcoloid.horizontalcompass.util.SimpleBukkitTask;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class ActionBarCompass extends Compass implements Runnable {
    private final SimpleBukkitTask bukkitTask;

    public ActionBarCompass(@NotNull Player player, @NotNull CompassDisplay display) {
        super(player, display);

        this.bukkitTask = new SimpleBukkitTask(this);
    }

    @Override
    public void show() {
        this.bukkitTask.runTaskTimer(0, 0);
    }

    @Override
    public void hide() {
        this.bukkitTask.cancel();
    }

    @Override
    public void run() {
        this.player.sendActionBar(this.display.getComponent());
    }
}
