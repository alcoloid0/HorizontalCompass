package com.github.alcoloid0.horizontalcompass.settings.holder;

import com.github.alcoloid0.horizontalcompass.compass.CompassType;
import com.github.alcoloid0.horizontalcompass.display.CompassDisplayType;
import net.kyori.adventure.bossbar.BossBar;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
@ConfigSerializable
public final class CompassSettingsHolder {
    private CompassType type = CompassType.BOSSBAR;
    private CompassDisplayType display = CompassDisplayType.RUST;
    @Setting("boss-bar")
    private BossBarSetting bossBar = new BossBarSetting();

    public @NotNull CompassType getType() {
        return this.type;
    }

    public @NotNull CompassDisplayType getDisplay() {
        return this.display;
    }

    public BossBarSetting getBossBar() {
        return bossBar;
    }

    @SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
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