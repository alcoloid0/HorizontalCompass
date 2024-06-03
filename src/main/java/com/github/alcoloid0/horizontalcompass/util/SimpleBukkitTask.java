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

package com.github.alcoloid0.horizontalcompass.util;

import com.github.alcoloid0.horizontalcompass.HorizontalCompass;
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