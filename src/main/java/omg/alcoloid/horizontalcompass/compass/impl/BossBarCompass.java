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

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import omg.alcoloid.horizontalcompass.compass.Compass;
import omg.alcoloid.horizontalcompass.compass.display.CompassDisplay;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class BossBarCompass extends Compass {
    private final BossBar bossBar;
    private boolean progress;

    public BossBarCompass(@NotNull Player player, @NotNull CompassDisplay display) {
        super(player, display);

        this.bossBar = BossBar.bossBar(Component.empty(),
                0,
                BossBar.Color.WHITE,
                BossBar.Overlay.PROGRESS);

        this.progress = false;
    }

    @Override
    public void update() {
        super.update();

        this.bossBar.name(this.display.getComponent());

        if (this.progress) {
            this.bossBar.progress(this.getPlayerLookAngle() / 360.0f);
        }
    }

    @Override
    public void show() {
        this.player.showBossBar(this.bossBar);
    }

    @Override
    public void hide() {
        this.player.hideBossBar(this.bossBar);
    }

    public void setColor(@NotNull BossBar.Color color) {
        this.bossBar.color(color);
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
    }
}