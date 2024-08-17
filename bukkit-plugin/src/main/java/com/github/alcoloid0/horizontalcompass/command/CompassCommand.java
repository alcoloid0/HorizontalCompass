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

package com.github.alcoloid0.horizontalcompass.command;

import com.github.alcoloid0.horizontalcompass.HorizontalCompass;
import com.github.alcoloid0.horizontalcompass.api.compass.Compass;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class CompassCommand implements TabExecutor {
    private static final String USE_PERMISSION = "horizontalcompass.use";

    private final HorizontalCompass compassPlugin;

    public CompassCommand(@NotNull HorizontalCompass compassPlugin) {
        this.compassPlugin = compassPlugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("This command can only be used by a player.");
            return true;
        }

        if (!commandSender.hasPermission(USE_PERMISSION)) {
            commandSender.sendMessage("You do not have permission to use this command.");
            return true;
        }

        if (args.length == 0) {
            commandSender.sendMessage("Please specify an argument: /" + label + " <show/hide>");
            return true;
        }

        Player player = (Player) commandSender;

        Compass compass = compassPlugin.getCompassByPlayer(player).orElseThrow(RuntimeException::new);

        switch (args[0].toLowerCase()) {
            case "show":
                compass.show();
                player.sendMessage("Compass is now visible.");
                break;
            case "hide":
                compass.hide();
                player.sendMessage("Compass is now hidden.");
                break;
            default:
                player.sendMessage("Unknown argument: " + args[0]);
                break;
        }

        return true;
    }

    @Override
    public @NotNull List<String> onTabComplete(@NotNull CommandSender commandSender,
                                               @NotNull Command command,
                                               @NotNull String label,
                                               @NotNull String[] args) {

        if (!commandSender.hasPermission(USE_PERMISSION) || args.length > 1) {
            return Collections.emptyList();
        }

        return StringUtil.copyPartialMatches(args[0], Arrays.asList("show", "hide"), new ArrayList<>());
    }
}
