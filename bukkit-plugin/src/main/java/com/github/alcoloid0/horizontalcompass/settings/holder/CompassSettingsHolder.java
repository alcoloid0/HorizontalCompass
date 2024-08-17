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

package com.github.alcoloid0.horizontalcompass.settings.holder;

import com.github.alcoloid0.horizontalcompass.settings.setting.CompassStyleTypeSetting;
import com.github.alcoloid0.horizontalcompass.settings.setting.CompassTypeSetting;
import net.kyori.adventure.bossbar.BossBar;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
@ConfigSerializable
public final class CompassSettingsHolder {
    private CompassTypeSetting type = CompassTypeSetting.BOSSBAR;
    @Setting("display")
    private CompassStyleTypeSetting style = CompassStyleTypeSetting.RUST;
    @Setting("boss-bar")
    private BossBarSetting bossBar = new BossBarSetting();

    public @NotNull CompassTypeSetting getType() {
        return this.type;
    }

    public @NotNull CompassStyleTypeSetting getStyle() {
        return this.style;
    }

    public BossBarSetting getBossBar() {
        return bossBar;
    }

    @ConfigSerializable
    public static final class BossBarSetting {
        private BossBar.Color color = BossBar.Color.WHITE;
        private BossBar.Overlay overlay = BossBar.Overlay.PROGRESS;
        @Setting("show-progress")
        private boolean showProgress = false;

        public BossBar.@NotNull Color getColor() {
            return color;
        }

        public BossBar.@NotNull Overlay getOverlay() {
            return overlay;
        }

        public boolean isShowProgress() {
            return showProgress;
        }
    }
}