package com.github.alcoloid0.horizontalcompass.homes.util;

import com.github.alcoloid0.horizontalcompass.homes.HorizontalCompassHomes;
import net.ess3.api.IUser;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public final class EssentialsUtil {
    private EssentialsUtil() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static @NotNull Map<String, Location> getHomes(@Nullable IUser user) {
        Map<String, Location> homes = new HashMap<>();

        if (user != null) {
            try {
                for (String homeName : user.getHomes()) {
                    homes.put(homeName, user.getHome(homeName));
                }
            } catch (Exception ignored) {
            }
        }

        return homes;
    }

    public static @NotNull Map<String, Location> getHomes(@NotNull Player player) {
        return EssentialsUtil.getHomes(HorizontalCompassHomes.getEssentials().getUser(player));
    }
}
