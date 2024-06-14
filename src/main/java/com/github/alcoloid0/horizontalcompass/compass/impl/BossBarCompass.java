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

package com.github.alcoloid0.horizontalcompass.compass.impl;

import com.github.alcoloid0.horizontalcompass.HorizontalCompass;
import com.github.alcoloid0.horizontalcompass.compass.AbstractCompass;
import com.github.alcoloid0.horizontalcompass.display.CompassDisplay;
import com.github.alcoloid0.horizontalcompass.settings.Settings;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class BossBarCompass extends AbstractCompass {
    private final BossBar bossBar;
    private final Audience audience;

    public BossBarCompass(@NotNull HorizontalCompass compassPlugin,
                          @NotNull Player player,
                          @NotNull CompassDisplay display) {

        super(player, display);

        this.bossBar = BossBar.bossBar(Component.empty(),
                0,
                Settings.compass().getBossBar().getColor(),
                Settings.compass().getBossBar().getOverlay());

        this.audience = compassPlugin.getAdventure().player(player);
    }

    @Override
    public void update() {
        super.update();

        this.bossBar.name(this.display.getComponent());

        if (Settings.compass().getBossBar().isShowProgress()) {
            this.bossBar.progress((this.player.getEyeLocation().getYaw() + 180.0f) / 360.0f);
        }
    }

    @Override
    public void show() {
        this.audience.showBossBar(this.bossBar);
    }

    @Override
    public void hide() {
        this.audience.hideBossBar(this.bossBar);
    }
}