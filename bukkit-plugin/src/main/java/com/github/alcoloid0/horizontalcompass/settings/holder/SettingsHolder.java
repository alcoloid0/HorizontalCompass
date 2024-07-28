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
