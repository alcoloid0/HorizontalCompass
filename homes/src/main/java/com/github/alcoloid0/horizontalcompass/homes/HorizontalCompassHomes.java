package com.github.alcoloid0.horizontalcompass.homes;

import com.earth2me.essentials.Essentials;
import com.github.alcoloid0.horizontalcompass.api.HorizontalCompassAPI;
import com.github.alcoloid0.horizontalcompass.homes.listener.HomeListener;
import com.github.alcoloid0.horizontalcompass.homes.listener.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class HorizontalCompassHomes extends JavaPlugin {
    private static Essentials essentials;
    private static HorizontalCompassAPI compassAPI;

    public static Essentials getEssentials() {
        return essentials;
    }

    public static HorizontalCompassAPI getCompassAPI() {
        return compassAPI;
    }

    @Override
    public void onEnable() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        compassAPI = Bukkit.getServicesManager().load(HorizontalCompassAPI.class);

        if (compassAPI == null) {
            this.getLogger().severe("Disabled because the HorizontalCompassAPI service could not be loaded");
            pluginManager.disablePlugin(this);
            return;
        }

        essentials = (Essentials) pluginManager.getPlugin("Essentials");

        if (essentials == null) {
            this.getLogger().severe("Disabled because no Essentials dependency found");
            pluginManager.disablePlugin(this);
            return;
        }

        pluginManager.registerEvents(new HomeListener(), this);
        pluginManager.registerEvents(new PlayerJoinListener(), this);
    }
}