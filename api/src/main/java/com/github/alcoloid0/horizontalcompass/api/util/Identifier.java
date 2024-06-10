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

package com.github.alcoloid0.horizontalcompass.api.util;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record Identifier(@NotNull String base, @NotNull String id) {
    public static @NotNull Identifier plugin(@NotNull Plugin plugin, @NotNull String id) {
        return new Identifier(plugin.getName(), id);
    }

    public static @NotNull Identifier uuid(@NotNull UUID uuid) {
        return new Identifier("uuid", uuid.toString());
    }

    public static @NotNull Identifier at(@NotNull String base, @NotNull String id) {
        return new Identifier(base, id);
    }

    public @NotNull String toFullString() {
        return "%s:%s".formatted(this.base, this.id);
    }
}