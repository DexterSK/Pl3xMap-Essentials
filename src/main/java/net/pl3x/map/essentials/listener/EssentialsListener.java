package net.pl3x.map.essentials.listener;

import net.ess3.api.events.VanishStatusChangeEvent;
import net.pl3x.map.api.Pl3xMapProvider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class EssentialsListener implements Listener {
    @EventHandler
    public void onVanishStatusChange(VanishStatusChangeEvent event) {
        UUID uuid = event.getAffected().getBase().getUniqueId();
        boolean vanished = event.getValue();
        Pl3xMapProvider.get().playerManager().hidden(uuid, vanished);
    }
}
