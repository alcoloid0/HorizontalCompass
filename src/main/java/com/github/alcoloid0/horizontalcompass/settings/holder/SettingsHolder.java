package com.github.alcoloid0.horizontalcompass.settings.holder;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@SuppressWarnings("FieldMayBeFinal")
@ConfigSerializable
public final class SettingsHolder {
    @Setting("compass-settings")
    private CompassSettingsHolder compassSettings = new CompassSettingsHolder();
    @Setting("display-settings")
    private DisplaySettingsHolder displaySettings = new DisplaySettingsHolder();
    @Setting("waypoint-settings")
    private WaypointSettingsHolder waypointSettings = new WaypointSettingsHolder();

    public CompassSettingsHolder getCompassSettings() {
        return compassSettings;
    }

    public DisplaySettingsHolder getDisplaySettings() {
        return displaySettings;
    }

    public WaypointSettingsHolder getWaypointSettings() {
        return waypointSettings;
    }
}
