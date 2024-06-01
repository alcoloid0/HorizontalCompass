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

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public final class HookManager {
    private final Plugin plugin;

    private EssentialsHook essentialsHook = null;
    private ProtocolLibHook protocolLibHook = null;

    public HookManager(Plugin plugin) {
        this.plugin = plugin;

        this.getPlugin("Essentials").ifPresent(ess -> {
            this.essentialsHook = new EssentialsHook(ess);
        });

        this.getPlugin("ProtocolLib").ifPresent(lib -> {
            this.protocolLibHook = new ProtocolLibHook(lib, this.plugin);
        });
    }

    public Optional<EssentialsHook> essentials() {
        return Optional.ofNullable(this.essentialsHook);
    }

    public Optional<ProtocolLibHook> protocolLib() {
        return Optional.ofNullable(this.protocolLibHook);
    }

    private Optional<Plugin> getPlugin(@NotNull String pluginName) {
        return Optional.ofNullable(Bukkit.getPluginManager().getPlugin(pluginName));
    }
}
