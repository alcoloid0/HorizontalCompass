package omg.alcoloid.horizontalcompass.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import omg.alcoloid.horizontalcompass.HorizontalCompass;
import org.jetbrains.annotations.NotNull;

public class LookListener extends PacketAdapter {
    private final HorizontalCompass compassPlugin;

    public LookListener(@NotNull HorizontalCompass compassPlugin) {
        super(compassPlugin, ListenerPriority.NORMAL, PacketType.Play.Client.LOOK);

        this.compassPlugin = compassPlugin;
    }

    @Override
    public void onPacketReceiving(@NotNull PacketEvent event) {
        this.compassPlugin.getPlayerCompassMap().get(event.getPlayer()).update();
    }
}
