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

package omg.alcoloid.horizontalcompass.hook;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketListener;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public final class ProtocolLibHook implements Hook {
    private final Plugin plugin;
    private final Plugin protocolLibPlugin;
    private final ProtocolManager protocolManager;

    public ProtocolLibHook(@NotNull Plugin protocolLib, @NotNull Plugin plugin) {
        this.plugin = plugin;
        this.protocolLibPlugin = protocolLib;
        this.protocolManager = ProtocolLibrary.getProtocolManager();
    }

    public void addPacketListener(@NotNull Object packetListener) {
        this.protocolManager.addPacketListener((PacketListener) packetListener);
    }

    @Override
    public @NotNull Plugin getPlugin() {
        return this.protocolLibPlugin;
    }
}
