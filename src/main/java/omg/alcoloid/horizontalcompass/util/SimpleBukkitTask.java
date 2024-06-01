package omg.alcoloid.horizontalcompass.util;

import omg.alcoloid.horizontalcompass.HorizontalCompass;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.Optional;
import java.util.function.Consumer;

public class SimpleBukkitTask {
    private final Plugin plugin;
    private final Runnable runnable;
    private final BukkitScheduler scheduler;
    private BukkitTask bukkitTask;

    public SimpleBukkitTask(Plugin plugin, Runnable runnable) {
        this.plugin = plugin;
        this.runnable = runnable;
        this.scheduler = Bukkit.getScheduler();
        this.bukkitTask = null;
    }

    public SimpleBukkitTask(Runnable runnable) {
        this(HorizontalCompass.getInstance(), runnable);
    }

    public void runTaskTimer(long delay, long period) {
        this.cancel();
        this.bukkitTask = this.scheduler.runTaskTimer(this.plugin, this.runnable, delay, period);
    }

    public void ifRunning(Consumer<BukkitTask> taskConsumer) {
        if (this.isRunning()) {
            taskConsumer.accept(this.bukkitTask);
        }
    }

    public boolean isRunning() {
        return this.bukkitTask != null && !this.bukkitTask.isCancelled();
    }

    public void cancel() {
        this.ifRunning(BukkitTask::cancel);
    }

    public Optional<BukkitTask> getBukkitTask() {
        return Optional.ofNullable(this.bukkitTask);
    }
}