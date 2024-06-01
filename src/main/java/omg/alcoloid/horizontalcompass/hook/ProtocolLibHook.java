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
