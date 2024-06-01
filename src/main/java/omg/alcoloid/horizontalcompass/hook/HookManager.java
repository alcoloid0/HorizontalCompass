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
