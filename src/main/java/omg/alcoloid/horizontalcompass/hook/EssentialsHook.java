package omg.alcoloid.horizontalcompass.hook;

import com.earth2me.essentials.IEssentials;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class EssentialsHook implements Hook {
    private final IEssentials essentials;

    public EssentialsHook(@NotNull Plugin essentials) {
        this.essentials = (IEssentials) essentials;
    }

    public List<String> getPlayerHomeList(@NotNull Player player) {
        return this.essentials.getUser(player).getHomes();
    }

    public Location getPlayerHome(@NotNull Player player, @NotNull String homeName) {
        return this.essentials.getUser(player).getHome(homeName);
    }

    @Override
    public @NotNull Plugin getPlugin() {
        return this.essentials;
    }
}
