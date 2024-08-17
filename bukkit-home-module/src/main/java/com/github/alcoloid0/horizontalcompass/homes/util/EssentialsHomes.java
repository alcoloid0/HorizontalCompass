package com.github.alcoloid0.horizontalcompass.homes.util;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EssentialsHomes {
    private static final Essentials ESSENTIALS;

    static {
        ESSENTIALS = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
    }

    private EssentialsHomes() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static @NotNull Location getHomeLocation(@NotNull Player player, @NotNull String homeName) {
        try {
            return Objects.requireNonNull(ESSENTIALS).getUser(player).getHome(homeName);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public static @NotNull Map<String, Location> getHomes(@NotNull Player player) {
        User user = Objects.requireNonNull(ESSENTIALS).getUser(player);
        Function<String, Location> function = homeName -> getHomeLocation(player, homeName);

        return user.getHomes().stream().collect(Collectors.toMap(Function.identity(), function));
    }
}
