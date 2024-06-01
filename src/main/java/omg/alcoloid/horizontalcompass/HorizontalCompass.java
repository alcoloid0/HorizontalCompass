package omg.alcoloid.horizontalcompass;

import omg.alcoloid.horizontalcompass.compass.Compass;
import omg.alcoloid.horizontalcompass.compass.factory.CompassFactory;
import omg.alcoloid.horizontalcompass.compass.factory.SettingsCompassFactory;
import omg.alcoloid.horizontalcompass.hook.HookManager;
import omg.alcoloid.horizontalcompass.listener.HomeListener;
import omg.alcoloid.horizontalcompass.listener.LookListener;
import omg.alcoloid.horizontalcompass.listener.PlayerListener;
import omg.alcoloid.horizontalcompass.settings.Settings;
import omg.alcoloid.horizontalcompass.waypoint.factory.SettingsWaypointFactory;
import omg.alcoloid.horizontalcompass.waypoint.factory.WaypointFactory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public final class HorizontalCompass extends JavaPlugin {
    private static HorizontalCompass instance;
    private final Map<Player, Compass> playerCompassMap = new HashMap<>();
    private Settings settings;
    private CompassFactory compassFactory;
    private WaypointFactory waypointFactory;
    private HookManager hookManager;

    public static HorizontalCompass getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        this.settings = new Settings(this).load();

        this.waypointFactory = new SettingsWaypointFactory(this.settings);
        this.compassFactory = new SettingsCompassFactory(this.settings, this.waypointFactory);

        this.hookManager = new HookManager(this);

        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerListener(this), this);

        if (this.hookManager.essentials().isPresent()) {
            pluginManager.registerEvents(new HomeListener(this), this);
        }

        this.hookManager.protocolLib().ifPresent(protocolLib -> {
            protocolLib.addPacketListener(new LookListener(this));
        });
    }

    public Map<Player, Compass> getPlayerCompassMap() {
        return this.playerCompassMap;
    }

    public CompassFactory getCompassFactory() {
        return compassFactory;
    }

    public WaypointFactory getWaypointFactory() {
        return waypointFactory;
    }

    public Settings getSettings() {
        return settings;
    }

    public HookManager getHookManager() {
        return hookManager;
    }
}
