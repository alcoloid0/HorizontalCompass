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

package omg.alcoloid.horizontalcompass.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import omg.alcoloid.horizontalcompass.HorizontalCompass;
import org.jetbrains.annotations.NotNull;

public class LookListener extends PacketAdapter {
    private final HorizontalCompass compassPlugin;

    public LookListener(@NotNull HorizontalCompass compassPlugin) {
        super(compassPlugin, ListenerPriority.NORMAL, PacketType.Play.Client.LOOK);

        this.compassPlugin = compassPlugin;
    }

    @Override
    public void onPacketReceiving(@NotNull PacketEvent event) {
        this.compassPlugin.getPlayerCompassMap().get(event.getPlayer()).update();
    }
}
