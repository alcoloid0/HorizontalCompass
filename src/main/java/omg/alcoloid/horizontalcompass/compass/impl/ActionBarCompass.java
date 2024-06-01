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
